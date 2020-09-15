package com.vincenterc.jumpingjack

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Stage
import kotlin.math.abs

class Koala(x: Float, y: Float, s: Stage) : BaseActor(x, y, s) {
    private var stand = loadTexture("koala/stand.png")
    private var walk: Animation<TextureRegion>

    private var walkAcceleration = 100f
    private var walkDeceleration = 200f
    private var maxHorizontalSpeed = 200f
    private var gravity = 700f
    private var maxVerticalSpeed = 1000f

    private var jump = loadTexture("koala/jump.png")
    private var jumpSpeed = 450f
    private var belowSensor = BaseActor(0f, 0f, s)

    init {
        var walkFileNames: Array<String> = arrayOf(
            "koala/walk-1.png", "koala/walk-2.png", "koala/walk-3.png", "koala/walk-2.png"
        )
        walk = loadAnimationFromFiles(walkFileNames, 0.2f, true)

        setBoundaryPolygon(8)

        belowSensor.loadTexture("white.png")
        belowSensor.setSize(this.width - 8f, 8f)
        belowSensor.setBoundaryRectangle()
        belowSensor.isVisible = true
    }

    override fun act(dt: Float) {
        super.act(dt)

        if (Gdx.input.isKeyPressed(Keys.LEFT))
            accelerationVec.add(-walkAcceleration, 0f)

        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            accelerationVec.add(walkAcceleration, 0f)

        if (!Gdx.input.isKeyPressed(Keys.LEFT)
            && !Gdx.input.isKeyPressed(Keys.RIGHT)
        ) {
            var decelerationAmount = walkDeceleration * dt
            var walkDirection = if (velocityVec.x > 0) 1f else -1f
            var walkSpeed = abs(velocityVec.x)

            walkSpeed -= decelerationAmount

            if (walkSpeed < 0) walkSpeed = 0f

            velocityVec.x = walkSpeed * walkDirection
        }

        accelerationVec.add(0f, -gravity)
        velocityVec.add(accelerationVec.x * dt, accelerationVec.y * dt)

        velocityVec.x = MathUtils.clamp(velocityVec.x, -maxHorizontalSpeed, maxHorizontalSpeed)
        velocityVec.y = MathUtils.clamp(velocityVec.y, -maxVerticalSpeed, maxVerticalSpeed)

        moveBy(velocityVec.x * dt, velocityVec.y * dt)
        accelerationVec.set(0f, 0f)

        belowSensor.setPosition(x + 4, y - 8)

        if (this.isOnSolid()) {
            belowSensor.color = Color.GREEN
            if (velocityVec.x == 0f) setAnimation(stand)
            else setAnimation(walk)
        } else {
            belowSensor.color = Color.RED
            setAnimation(jump)
        }

        if (velocityVec.x > 0) scaleX = 1f
        if (velocityVec.x < 0) scaleX = -1f

        alignCamera()
        boundToWorld()
    }

    fun belowOverlaps(actor: BaseActor) = belowSensor.overlaps(actor)

    fun isOnSolid(): Boolean {
        for (actor in BaseActor.getList(stage, Solid::class.java.name)) {
            var solid = actor as Solid
            if (belowOverlaps(solid) && solid.isEnabled()) return true
        }

        return false
    }

    fun jump() {
        velocityVec.y = jumpSpeed
    }

    fun isFalling() = velocityVec.y < 0

    fun spring() {
        velocityVec.y = 1.5f * jumpSpeed
    }

    fun isJumping() = velocityVec.y > 0
}
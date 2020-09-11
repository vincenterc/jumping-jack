package com.vincenterc.jumpingjack

import com.badlogic.gdx.Input.Keys
import kotlin.math.abs

class LevelScreen : BaseScreen() {
    lateinit var jack: Koala

    override fun initialize() {
        var tma = TilemapActor("map.tmx", mainStage)

        for (obj in tma.getRectangleList("Solid")) {
            var props = obj.properties
            Solid(
                props.get("x") as Float,
                props.get("y") as Float,
                props.get("width") as Float,
                props.get("height") as Float,
                mainStage
            )
        }

        var startPoint = tma.getRectangleList("start")[0]
        var startProps = startPoint.properties
        jack = Koala(startProps.get("x") as Float, startProps.get("y") as Float, mainStage)
    }

    override fun update(dt: Float) {
        for (actor in BaseActor.getList(mainStage, Solid::class.java.name)) {
            var solid = actor as Solid

            if (jack.overlaps(solid) && solid.isEnabled()) {
                var offset = jack.preventOverlap(solid)

                if (offset != null) {
                    if (abs(offset.x) > abs(offset.y)) {
                        jack.velocityVec.x = 0f
                    } else {
                        jack.velocityVec.y = 0f
                    }
                }
            }
        }
    }

    override fun keyDown(keyCode: Int): Boolean {
        if (keyCode == Keys.SPACE) {
            if (jack.isOnSolid()) jack.jump()
        }
        return false
    }
}
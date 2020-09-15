package com.vincenterc.jumpingjack

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import kotlin.math.abs

class LevelScreen : BaseScreen() {
    lateinit var jack: Koala

    var gameOver = false
    var coins = 0
    var time = 60

    private lateinit var coinLabel: Label
    private lateinit var timeLabel: Label
    private lateinit var messageLabel: Label
    private lateinit var keyTable: Table

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

        coinLabel = Label("Coins: $coins", BaseGame.labelStyle)
        coinLabel.color = Color.GOLD
        timeLabel = Label("Time: $time", BaseGame.labelStyle)
        timeLabel.color = Color.LIGHT_GRAY
        messageLabel = Label("Message", BaseGame.labelStyle)
        messageLabel.isVisible = false
        keyTable = Table()

        uiTable.pad(20f)
        uiTable.add(coinLabel)
        uiTable.add(keyTable).expandX()
        uiTable.add(timeLabel)
        uiTable.row()
        uiTable.add(messageLabel).colspan(3).expandY()
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
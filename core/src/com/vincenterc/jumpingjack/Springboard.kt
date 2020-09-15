package com.vincenterc.jumpingjack

import com.badlogic.gdx.scenes.scene2d.Stage

class Springboard(x: Float, y: Float, s: Stage) : BaseActor(x, y, s) {
    init {
        loadAnimationFromSheet(
            "items/springboard.png", 1, 3, 0.2f, true
        )
    }
}
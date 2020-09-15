package com.vincenterc.jumpingjack

import com.badlogic.gdx.scenes.scene2d.Stage

class Flag(x: Float, y: Float, s: Stage) : BaseActor(x, y, s) {
    init {
        loadAnimationFromSheet(
            "items/flag.png", 1, 2, 0.2f, true
        )
    }
}
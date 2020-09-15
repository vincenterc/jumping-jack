package com.vincenterc.jumpingjack

import com.badlogic.gdx.scenes.scene2d.Stage

class Platform(
    x: Float, y: Float, s: Stage
) : Solid(x, y, 32f, 16f, s) {
    init {
        loadTexture("items/platform.png")
    }
}
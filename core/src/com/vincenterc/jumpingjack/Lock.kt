package com.vincenterc.jumpingjack

import com.badlogic.gdx.scenes.scene2d.Stage

class Lock(
    x: Float, y: Float, s: Stage
) : Solid(x, y, 32f, 32f, s) {
    init {
        loadTexture("items/lock.png")
    }
}
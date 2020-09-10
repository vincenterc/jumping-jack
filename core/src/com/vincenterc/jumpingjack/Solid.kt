package com.vincenterc.jumpingjack

import com.badlogic.gdx.scenes.scene2d.Stage

class Solid(
    x: Float, y: Float, width: Float, height: Float, s: Stage
) : BaseActor(x, y, s) {
    private var enabled = true

    init {
        setSize(width, height)
        setBoundaryRectangle()
    }

    fun setEnabled(b: Boolean) {
        enabled = b
    }

    fun isEnabled() = enabled
}
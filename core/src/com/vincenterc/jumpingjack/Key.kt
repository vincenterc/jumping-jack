package com.vincenterc.jumpingjack

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions

class Key(x: Float, y: Float, s: Stage) : BaseActor(x, y, s) {
    init {
        loadTexture("items/key.png")

        rotateBy(10f)

        var tilt = Actions.sequence(
            Actions.rotateBy(-20f, 0.5f),
            Actions.rotateBy(20f, 0.5f)
        )

        addAction(Actions.forever(tilt))
    }
}
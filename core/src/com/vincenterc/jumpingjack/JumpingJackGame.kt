package com.vincenterc.jumpingjack

class JumpingJackGame : BaseGame() {
    override fun create() {
        super.create()
        setActiveScreen(LevelScreen())
    }
}
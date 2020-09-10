package com.vincenterc.jumpingjack.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.vincenterc.jumpingjack.JumpingJackGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration().apply {
            title = "Jumping Jack"
            width = 800
            height = 640
        }
        LwjglApplication(JumpingJackGame(), config)
    }
}
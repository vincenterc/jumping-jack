package com.vincenterc.jumpingjack

class LevelScreen : BaseScreen() {

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
    }

    override fun update(dt: Float) {}
}
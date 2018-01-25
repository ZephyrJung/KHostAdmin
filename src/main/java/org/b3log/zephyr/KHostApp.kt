package org.b3log.zephyr

import javafx.application.Application
import javafx.scene.image.Image
import javafx.stage.Stage
import org.b3log.zephyr.constants.Config
import org.b3log.zephyr.utils.HostUtil
import org.b3log.zephyr.views.KHostMainView
import tornadofx.*

class KHostApp : App(KHostMainView::class){
    override fun start(stage: Stage) {
        stage.icons += Image(Config.getAppIconPath())
        super.start(stage)
    }

    override fun stop() {
        super.stop()
        HostUtil.resetHost()
    }

    override fun init() {
        super.init()

    }
}


fun main(args: Array<String>) {
    HostUtil.init()
    Application.launch(KHostApp::class.java, *args)
}
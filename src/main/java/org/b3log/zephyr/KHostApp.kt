package org.b3log.zephyr

import javafx.application.Application
import javafx.scene.image.Image
import org.b3log.zephyr.utils.HostUtil
import org.b3log.zephyr.views.KHostMainView
import tornadofx.*
import java.io.File

class KHostApp : App(KHostMainView::class) {

    override fun stop() {
        super.stop()
        HostUtil.resetHost()
    }

    override fun init() {
        super.init()
        val image = Image(File("khost.png").toURI().toString())
        setStageIcon(image)
    }
}


fun main(args: Array<String>) {
    HostUtil.init()
    Application.launch(KHostApp::class.java, *args)
}
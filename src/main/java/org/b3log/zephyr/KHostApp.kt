package org.b3log.zephyr

import javafx.application.Application
import org.b3log.zephyr.utils.HostUtil
import org.b3log.zephyr.views.KHostMainView
import tornadofx.*

class KHostApp : App(KHostMainView::class){
    override fun stop() {
        super.stop()
        HostUtil.resetHost()
    }
}


fun main(args: Array<String>) {
    HostUtil.init()
    Application.launch(KHostApp::class.java, *args)
}
package org.b3log.zephyr

import javafx.application.Application
import org.b3log.zephyr.utils.HostUtil
import org.b3log.zephyr.views.KHostMainView
import tornadofx.*

class WithFXPropertiesApp : App(KHostMainView::class)


fun main(args: Array<String>) {
    HostUtil.init()
    Application.launch(WithFXPropertiesApp::class.java, *args)
}
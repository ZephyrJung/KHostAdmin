package org.b3log.zephyr

import javafx.application.Application
import org.b3log.zephyr.views.ItemViewModelWithFxMainView
import tornadofx.App

class WithFXPropertiesApp : App(ItemViewModelWithFxMainView::class)


fun main(args: Array<String>) {
    Application.launch(WithFXPropertiesApp::class.java, *args)
}
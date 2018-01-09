package org.b3log.zephyr

import javafx.application.Application
import org.b3log.zephyr.views.KHostMainView
import tornadofx.*

class WithFXPropertiesApp : App(KHostMainView::class)


fun main(args: Array<String>) {
    Application.launch(WithFXPropertiesApp::class.java, *args)

//    val test = HostJson(true,"127.0.0.1","test")
//    val gson = Gson()
//    println(gson.toJson(Arrays.asList(test,test)).toString())
}
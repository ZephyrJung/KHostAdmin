package org.b3log.zephyr.controller

import javafx.collections.FXCollections
import org.b3log.zephyr.model.*
import tornadofx.Controller

class ProfileController : Controller() {
    val profiles = FXCollections.observableArrayList<Profile>()!!
    val selectedProfile = ProfileModel()

    init {
        // Add some test persons for the demo
        profiles.add(Profile(0,"Main",getHosts()))
        profiles.add(Profile(1, "Prod", listOf(Host(true, "192.168.0.1","local host",1))))
        profiles.add(Profile(2, "Uat", listOf(
                Host(true, "192.168.0.2","local host",2),
                Host(true, "192.168.0.3","local host",2)
        )))
    }

    private fun getHosts(): List<Host> {
        return listOf(
                Host(true, "192.168.0.1","local host",0),
                Host(false, "192.168.0.2","local host",0),
                Host(true, "192.168.0.3","local host",0),
                Host(true, "192.168.0.4","local host",0),
                Host(true, "192.168.0.5","local host",0)
        )
    }
}
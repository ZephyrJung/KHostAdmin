package org.b3log.zephyr.controller

import javafx.collections.FXCollections
import org.b3log.zephyr.utils.HostUtil
import org.b3log.zephyr.utils.JsonUtil
import org.b3log.zephyr.views.model.Host
import org.b3log.zephyr.views.model.Profile
import org.b3log.zephyr.views.model.ProfileModel
import tornadofx.*

class ProfileController : Controller() {
    val profiles = FXCollections.observableArrayList<Profile>()!!
    val selectedProfile = ProfileModel()

    init {
        // Add some test persons for the demo
        profiles.add(Profile(0,"Main",getHosts("Main")))
//        profiles.add(Profile(1, "Prod", listOf(Host(true, "192.168.0.1","local host",1))))
//        profiles.add(Profile(2, "Uat", listOf(
//                Host(true, "192.168.0.2","local host",2),
//                Host(true, "192.168.0.3","local host",2)
//        )))
    }

    private fun getHosts(profile:String): List<Host> {
        return JsonUtil.getHostFromJson(HostUtil.readProfile(profile))
    }
}
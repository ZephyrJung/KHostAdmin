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
        profiles.add(Profile(0,"Main",getHosts("Main")))
        profiles.addAll(HostUtil.readProfiles())
    }

    private fun getHosts(profile:String): List<Host> {
        return JsonUtil.getHostFromJson(HostUtil.readProfile(profile))
    }
}
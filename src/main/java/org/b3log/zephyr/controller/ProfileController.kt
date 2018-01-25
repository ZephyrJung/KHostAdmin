package org.b3log.zephyr.controller

import javafx.collections.FXCollections
import org.b3log.zephyr.constants.Config
import org.b3log.zephyr.utils.HostUtil
import org.b3log.zephyr.utils.HostUtil.getHosts
import org.b3log.zephyr.views.model.Profile
import org.b3log.zephyr.views.model.ProfileModel
import tornadofx.*

class ProfileController : Controller() {
    val profiles = FXCollections.observableArrayList<Profile>()!!
    val selectedProfile = ProfileModel()

    init {
        profiles.add(Profile(Config.Common, getHosts("Common")))
        profiles.addAll(HostUtil.readProfiles())
    }
}
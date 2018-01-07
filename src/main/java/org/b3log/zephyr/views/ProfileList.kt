package org.b3log.zephyr.views

import org.b3log.zephyr.controller.PersonController
import org.b3log.zephyr.controller.ProfileController
import org.b3log.zephyr.model.Person
import org.b3log.zephyr.model.Profile
import tornadofx.*

class ProfileList : View() {
    val controller: ProfileController by inject()

    override val root = tableview(controller.profiles) {
        column("Id", Profile::idProperty)
        column("Name", Profile::nameProperty)
        bindSelected(controller.selectedProfile)
        smartResize()
    }
}

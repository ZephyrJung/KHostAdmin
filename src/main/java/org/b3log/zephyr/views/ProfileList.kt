package org.b3log.zephyr.views

import org.b3log.zephyr.controller.ProfileController
import org.b3log.zephyr.views.model.Profile
import tornadofx.*

class ProfileList : View() {
    val controller: ProfileController by inject()

    override val root = tableview(controller.profiles) {
        prefWidth = 180.0
        minWidth = 100.0
        column("Id", Profile::idProperty).minWidth(20)
        column("Name", Profile::nameProperty).minWidth(100).makeEditable().setOnEditCommit {
            println(it.tablePosition.row)
        }
        bindSelected(controller.selectedProfile)
        smartResize()
    }
}

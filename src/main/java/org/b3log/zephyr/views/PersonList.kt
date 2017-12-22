package org.b3log.zephyr.views

import org.b3log.zephyr.controller.PersonController
import org.b3log.zephyr.model.Person
import tornadofx.*

class PersonList : View() {
    val controller: PersonController by inject()

    override val root = tableview(controller.persons) {
        column("Id", Person::idProperty)
        column("Name", Person::nameProperty)
        bindSelected(controller.selectedPerson)
        smartResize()
    }
}

package org.b3log.zephyr.controller

import javafx.collections.FXCollections
import org.b3log.zephyr.model.Person
import org.b3log.zephyr.model.PersonModel
import org.b3log.zephyr.model.PhoneNumber
import tornadofx.Controller

class PersonController : Controller() {
    val persons = FXCollections.observableArrayList<Person>()
    val selectedPerson = PersonModel()

    init {
        // Add some test persons for the demo
        persons.add(Person(42, "John Doe", listOf(PhoneNumber("47", "33349700"), PhoneNumber("47", "333943222"))))
        persons.add(Person(43, "Jane Doe", listOf(PhoneNumber("1", "312 213 21"))))
    }
}
package org.b3log.zephyr.views

import tornadofx.*


class ItemViewModelWithFxMainView : View("Person Editor") {
    override val root = hbox {
        add<PersonList>()
        add<PersonEditor>()
    }
}

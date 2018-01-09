package org.b3log.zephyr.views

import tornadofx.*


class KHostMainView : View("Person Editor") {
    override val root = hbox {
        add<ProfileList>()
        add<ProfileEditor>()
    }
}

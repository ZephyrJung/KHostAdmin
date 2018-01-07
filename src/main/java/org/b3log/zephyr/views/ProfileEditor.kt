package org.b3log.zephyr.views

import javafx.scene.control.TableView
import org.b3log.zephyr.controller.PersonController
import org.b3log.zephyr.controller.ProfileController
import org.b3log.zephyr.model.Host
import org.b3log.zephyr.model.PhoneNumber
import tornadofx.*

class ProfileEditor : View() {
    val controller: ProfileController by inject()
    var hostTable: TableView<Host> by singleAssign()

    override val root = form {
        fieldset("Profile Information") {
            field("Name") {
                textfield(controller.selectedProfile.name)
            }
            button("Save") {
                setOnAction {
                    controller.selectedProfile.commit()
                }
            }
        }
        fieldset("Host List") {
            vbox(5.0) {
                tableview<Host> {
                    hostTable = this
                    isEditable = true
                    smartResize()
                    column("enable",Host::enable).makeEditable()
                    column("ip", Host::ip).makeEditable()
                    column("domain", Host::domain).makeEditable()
                    itemsProperty().bind(controller.selectedProfile.hosts)
                }
                button("Add host") {
                    setOnAction {
                        val newHost = Host(true, "","",-1)
                        controller.selectedProfile.hosts.value.add(newHost)
                        hostTable.selectionModel.select(newHost)
                        hostTable.edit(hostTable.items.size - 1, hostTable.columns.first())
                    }
                }
            }
        }
    }
}

package org.b3log.zephyr.views

import javafx.scene.control.TableView
import org.b3log.zephyr.controller.ProfileController
import org.b3log.zephyr.model.Host
import tornadofx.*

class ProfileEditor : View() {
    val controller: ProfileController by inject()
    var hostTable: TableView<Host> by singleAssign()

    override val root = form {
        prefWidth=400.0
        /*fieldset("Profile Information") {
            field("Name") {
                textfield(controller.selectedProfile.name)
            }
            button("Save") {
                setOnAction {
                    controller.selectedProfile.commit()
                }
            }
        }*/
        fieldset("Host List") {
            vbox(5.0) {
                tableview<Host> {
                    hostTable = this
                    isEditable = true
                    smartResize()
                    column("",Host::enable).makeEditable().minWidth(20)
                    column("IP地址", Host::ip).minWidth(150)
                    column("域名", Host::domain).minWidth(150)
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

package org.b3log.zephyr.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.control.TableView
import org.b3log.zephyr.constants.Config
import org.b3log.zephyr.controller.ProfileController
import org.b3log.zephyr.utils.HostUtil
import org.b3log.zephyr.views.model.Host
import org.b3log.zephyr.views.model.Profile
import tornadofx.*
import java.io.File
import java.util.*

class ProfileEditor : View() {
    val controller: ProfileController by inject()

    var hostTable: TableView<Host> by singleAssign()

    override val root = form {
        prefWidth = 450.0
        fieldset("Host List") {
            vbox(5.0) {
                hbox(5.0) {
                    alignment = Pos.CENTER_LEFT
                    label("查找")
                    textfield {
                        minWidth = 200.0
                        prefWidth = 200.0
                    }
                }
                tableview<Host> {
                    hostTable = this
                    isEditable = true
                    smartResize()
                    column("", Host::enable).useCheckbox(editable = true).minWidth(20)
                    column("IP地址", Host::ip).minWidth(150)
                    column("域名", Host::domain).minWidth(150)
                    itemsProperty().bind(controller.selectedProfile.hosts)
                }
                buttonbar {
                    button("Add Profile") {
                        setOnAction {
                            dialog("Add new profile") {
                                minWidth = 200.0
                                prefWidth = 200.0
                                val model = ViewModel()
                                val profile = model.bind { SimpleStringProperty() }
                                field("Profile") {
                                    textfield(profile) {
                                        required()
                                    }
                                }
                                buttonbar {
                                    button("Save").action {
                                        model.commit { saveProfile(profile.value) }
                                    }
                                }
                            }
                        }
                    }
                    button("Add Host") {
                        setOnAction {
                            //todo 我脚的这样写不太对吼。。。
                            dialog("添加Host(默认启用)") {
                                minWidth = 200.0
                                prefWidth = 200.0
                                val model = ViewModel()
                                val ip = model.bind { SimpleStringProperty() }
                                val domain = model.bind { SimpleStringProperty() }
                                val comment = model.bind { SimpleStringProperty() }
                                field("IP") {
                                    textfield(ip) {
                                        required()
                                    }
                                }
                                field("域名") {
                                    textfield(domain) {
                                        required()
                                    }
                                }
                                field("说明") {
                                    textfield(comment) {
                                    }
                                }
                                buttonbar {
                                    button("Save").action {
                                        model.commit { saveHost(ip.value, domain.value, comment.value) }
                                    }
                                }
                            }
                        }
                    }
                    button("Active") {
                        setOnAction {
                            val result = activeHosts()
                            if (result) {
                                alert(Alert.AlertType.INFORMATION, "", "启用成功", ButtonType.OK)
                            } else {
                                alert(Alert.AlertType.ERROR, "", "启用失败", ButtonType.OK)
                            }
                        }
                    }
                    button("Export") {
                        setOnAction {
                            dialog("导出到Host文件（慎用）") {
                                exportHosts()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun saveProfile(profile: String) {
        val file = File(Config.getProfileHostPath(profile))
        if (file.exists()) {
            val alert = Alert(Alert.AlertType.WARNING, "profile文件已存在，是否覆盖？", ButtonType.NO, ButtonType.YES)
            alert.headerText = ""
            val result = alert.showAndWait().get()
            if (result == ButtonType.NO) {
                return
            } else if (result == ButtonType.YES) {
                file.writer().flush()
                file.writer().close()
                return
            }
        }
        file.writer().flush()
        file.writer().close()
        controller.profiles.add(Profile(controller.profiles.size, profile, Arrays.asList()))
    }

    private fun saveHost(ip: String, domain: String, comment: String) {
        val newHost = Host(true, ip, domain, comment)
        controller.selectedProfile.hosts.value.add(newHost)
        hostTable.selectionModel.select(newHost)
        hostTable.edit(hostTable.items.size - 1, hostTable.columns.first())
    }

    private fun activeHosts(): Boolean {
        HostUtil.writeProfile(controller.selectedProfile.name.value,controller.selectedProfile.hosts.value)
        return HostUtil.writeHosts(controller.selectedProfile.hosts.value, Config.hostPath)
    }

    private fun exportHosts(): Boolean {
        return HostUtil.writeHosts(controller.selectedProfile.hosts.value, Config.backupPath)
    }
}

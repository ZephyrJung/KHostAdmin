package org.b3log.zephyr.utils

import javafx.collections.ObservableList
import org.b3log.zephyr.constants.Config.backupPath
import org.b3log.zephyr.constants.Config.filePath
import org.b3log.zephyr.constants.Config.getProfileHostPath
import org.b3log.zephyr.constants.Config.hostPath
import org.b3log.zephyr.controller.ProfileController
import org.b3log.zephyr.utils.model.HostJson
import org.b3log.zephyr.views.model.Host
import org.b3log.zephyr.views.model.Profile
import sun.text.normalizer.UTF16.append
import java.io.File
import java.io.InputStream

/**
 * @author Zhang Yu
 * Date: 18年1月9日
 * Email: yu.zhang@7fresh.com
 */
object HostUtil {

    fun init() {
        val file = File(filePath)
        if (!file.isDirectory) {
            file.mkdir()
        }
        backupMainHost()
    }

    fun backupMainHost() {
        val hostParttern = "[0-9]+.[0-9]+.[0-9]+.[0-9]+".toRegex()
        val hostList = mutableListOf<HostJson>()

        val inputStream: InputStream = File(hostPath).inputStream()
        val strBuilder = StringBuilder()
        inputStream.bufferedReader().useLines { lines ->
            lines.forEach {
                strBuilder.append(it).append("\n")
                val host = hostParttern.find(it)
                if (host != null) {
                    try {
                        hostList.add(
                                HostJson(
                                        enable = !it.trim().startsWith("#"),
                                        ip = host.value,
                                        domain = if (it.lastIndexOf("#") <= 0) it.substring(it.indexOf(host.value) + host.value.length + 1).trim() else it.substring(it.indexOf(host.value) + host.value.length + 1, it.lastIndexOf("#")).trim(),
                                        comment = if (it.lastIndexOf("#") <= 0) "" else it.substring(it.lastIndexOf("#") + 1).trim()
                                )
                        )
                    } catch (e: Exception) {
                        println("解析失败： " + it)
                    }
                }
            }
        }
        inputStream.bufferedReader().close()
        File(filePath + File.separator + "host.bak").bufferedWriter().use { out -> out.write(strBuilder.toString()) }
        File(getProfileHostPath("Main")).bufferedWriter().use { out -> out.write(JsonUtil.getJsonFromHost(hostList)) }

    }

    fun readProfile(profile: String): String {
        val inputStream: InputStream = File(getProfileHostPath(profile)).inputStream()
        val json = inputStream.bufferedReader().readLine()
        inputStream.bufferedReader().close()
        return json
    }

    fun writeHosts(hosts: List<Host>, path: String): Boolean {
        try {
            val out = StringBuilder()
            hosts.forEach { host ->
                out.append(if (host.enable) {
                    "#"
                } else "").append(" ")
                        .append(host.ip).append(" ")
                        .append(host.domain)
                if (host.comment.isNotBlank()) {
                    out.append(" #").append(host.comment)
                }
                out.append("\n")
            }
            File(path).bufferedWriter().use { o -> o.write(out.toString()) }
            return true
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    fun resetHost() {
        val inputStream: InputStream = File(backupPath).inputStream()
        val strBuilder = StringBuilder()
        inputStream.bufferedReader().useLines { lines ->
            lines.forEach {
                strBuilder.append(it).append("\n")
            }
        }
        inputStream.bufferedReader().close()
        File(hostPath).bufferedWriter().use { out -> out.write(strBuilder.toString()) }
    }

    fun writeProfile(profile: String, hosts: List<Host>) {
        val hostList = mutableListOf<HostJson>()
        hosts.forEach { host ->
            hostList.add(HostJson(
                    enable = host.enable,
                    ip = host.ip,
                    domain = host.domain,
                    comment = host.comment
            ))
        }
        File(getProfileHostPath(profile)).bufferedWriter().use { out -> out.write(JsonUtil.getJsonFromHost(hostList)) }
    }

    fun readProfiles(): Profile {

    }
}
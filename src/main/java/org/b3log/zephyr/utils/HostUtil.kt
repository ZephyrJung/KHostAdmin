package org.b3log.zephyr.utils

import org.b3log.zephyr.constants.Config
import org.b3log.zephyr.constants.Config.appPath
import org.b3log.zephyr.constants.Config.backupPath
import org.b3log.zephyr.constants.Config.getProfileHostPath
import org.b3log.zephyr.constants.Config.hostPath
import org.b3log.zephyr.utils.model.HostJson
import org.b3log.zephyr.views.model.Host
import org.b3log.zephyr.views.model.Profile
import java.io.File
import java.io.InputStream

/**
 * @author Zhang Yu
 * Date: 18年1月9日
 * Email: yu.zhang@7fresh.com
 */
object HostUtil {

    fun init() {
        val file = File(appPath)
        if (!file.isDirectory) {
            file.mkdir()
        }
        backupMainHost()
    }

    private fun backupMainHost() {
        val hostPattern = "[0-9]+.[0-9]+.[0-9]+.[0-9]+".toRegex()
        val hostList = mutableListOf<HostJson>()

        val inputStream: InputStream = File(hostPath).inputStream()
        val strBuilder = StringBuilder()
        inputStream.bufferedReader().useLines { lines ->
            lines.forEach {
                strBuilder.append(it).append("\n")
                val host = hostPattern.find(it)
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
        File(appPath + File.separator + "host.bak").bufferedWriter().use { out -> out.write(strBuilder.toString()) }
        //创建默认带的Common Profile
        val file = File(getProfileHostPath(Config.Common))
        if(!file.exists()) {
            File(getProfileHostPath(Config.Common)).bufferedWriter().use { out -> out.write("") }
        }
    }

    private fun readProfile(profile: String): String {
        val inputStream: InputStream = File(getProfileHostPath(profile)).inputStream()
        val json = inputStream.bufferedReader().readLine()
        inputStream.bufferedReader().close()
        return json ?: ""
    }

    fun writeHosts(hosts: List<Host>, path: String): Boolean {
        try {
            val common = JsonUtil.getHostFromJson(HostUtil.readProfile(Config.Common))
            val out = StringBuilder()
            common.forEach { host ->
                out.append(if (!host.enable) {
                    "#"
                } else "").append(" ")
                        .append(host.ip).append(" ")
                        .append(host.domain)
                if (host.comment.isNotBlank()) {
                    out.append(" #").append(host.comment)
                }
                out.append("\n")
            }

            hosts.forEach { host ->
                out.append(if (!host.enable) {
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
            println(e.stackTrace)
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

    fun readProfiles(): List<Profile> {
        val profiles = mutableListOf<Profile>()
        val dir = File(appPath)
        val files = dir.listFiles()
        files.forEach { f ->
            if (f.isFile && f.name.endsWith(".json") && f.name != Config.Common + ".json") {
                profiles.add(Profile(f.name.removeSuffix(".json"), getHosts(f.name.removeSuffix(".json"))))
            }
        }
        return profiles
    }

    fun getHosts(profile: String): List<Host> {
        return try {
            JsonUtil.getHostFromJson(HostUtil.readProfile(profile))
        } catch (e: Exception) {
            listOf()
        }
    }
}

package org.b3log.zephyr.utils

import org.b3log.zephyr.constants.Config.filePath
import org.b3log.zephyr.constants.Config.getProfileHostPath
import org.b3log.zephyr.constants.Config.hostPath
import org.b3log.zephyr.utils.model.HostJson
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

    fun saveMainHost(hosts: List<HostJson>) {

    }

    fun saveProfile(profileId: Int, profileName: String, hosts: List<HostJson>) {

    }

    fun readProfile(profile: String): String {
        val inputStream: InputStream = File(getProfileHostPath(profile)).inputStream()
        val json = inputStream.bufferedReader().readLine()
        inputStream.bufferedReader().close()
        return json
    }
}
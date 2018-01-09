package org.b3log.zephyr.utils

import org.b3log.zephyr.utils.model.HostJson
import java.io.File
import java.io.InputStream

/**
 * @author Zhang Yu
 * Date: 18年1月9日
 * Email: yu.zhang@7fresh.com
 */
class HostUtil {
    private val hostPath = "/etc/hosts"
    private val filePath = System.getProperty("user.home") + File.separator + ".khost"

    fun init() {
        val file = File(filePath)
        if (!file.isDirectory) {
            file.mkdir()
        }
        backupMainHost()
    }

    fun backupMainHost() {
        val inputStream: InputStream = File(hostPath).inputStream()
        val strBuilder = StringBuilder()
        inputStream.bufferedReader().useLines { lines -> lines.forEach { strBuilder.append(it).append("\n") } }
        File(filePath + File.separator + "host.bak").bufferedWriter().use { out -> out.write(strBuilder.toString()) }
    }

    fun saveMainHost(hosts: List<HostJson>) {

    }

    fun saveProfile(profileId: Int, profileName: String, hosts: List<HostJson>) {

    }
}

fun main(args: Array<String>) {
    val t = HostUtil()
    t.init()
}
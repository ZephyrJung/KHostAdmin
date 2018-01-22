package org.b3log.zephyr.constants

import java.io.File

/**
 * @author Zhang Yu
 * Date: 18年1月10日
 * Email: yu.zhang@7fresh.com
 */
object Config {
    var backupHosts = ""
    val hostPath = "/etc/hosts"
//    val hostPath = System.getProperty("user.home") + File.separator + ".khost/host"
    val backupPath = System.getProperty("user.home") + File.separator + ".khost/host.bak"
    val filePath = System.getProperty("user.home") + File.separator + ".khost"

    fun getProfileHostPath(profile: String): String {
        return filePath+File.separator+profile+".json"
    }

    //TODO add banner for profile
    val banner = "#***-==>ProfileName<==-***#"
}
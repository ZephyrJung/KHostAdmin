package org.b3log.zephyr.constants

import java.io.File

/**
 * @author Zhang Yu
 * Date: 18年1月10日
 * Email: yu.zhang@7fresh.com
 */
object Config {

    val hostPath = "/etc/hosts"
//    val hostPath = System.getProperty("user.home") + File.separator + ".khost/host"
    val backupPath = System.getProperty("user.home") + File.separator + ".khost/host.bak"
    val appPath = System.getProperty("user.home") + File.separator + ".khost"

    fun getProfileHostPath(profile: String): String {
        return appPath +File.separator+profile+".json"
    }

    fun getAppIconPath():String{
        return "file://"+appPath+File.separator+"khost.png"
    }

    val Common = "Common"
}
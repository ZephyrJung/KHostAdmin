package org.b3log.zephyr.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.b3log.zephyr.utils.model.HostJson
import org.b3log.zephyr.views.model.Host

/**
 * @author Zhang Yu
 * Date: 18年1月9日
 * Email: yu.zhang@7fresh.com
 */
object JsonUtil {
    private val gson = Gson()
    fun getJsonFromHost(host: List<HostJson>): String {
        return if(host.isNotEmpty()) {
            gson.toJson(host).toString()
        }else{
            ""
        }
    }

    fun getHostFromJson(json: String): List<Host> {
        return if(json.isNotBlank()) {
            val hostList = gson.fromJson<List<HostJson>>(json, object : TypeToken<List<HostJson>>() {}.type)
            val hosts = mutableListOf<Host>()
            hostList.forEach { h ->
                hosts.add(Host(
                        enable = h.enable,
                        ip = h.ip,
                        domain = h.domain,
                        comment = h.comment
                ))
            }
            hosts
        }else{
            listOf()
        }
    }
}
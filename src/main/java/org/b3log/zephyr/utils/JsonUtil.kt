package org.b3log.zephyr.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.b3log.zephyr.utils.model.HostJson

/**
 * @author Zhang Yu
 * Date: 18年1月9日
 * Email: yu.zhang@7fresh.com
 */
class JsonUtil {
    private val gson = Gson()
    fun getJsonFromHost(host: List<HostJson>): String {
        return gson.toJson(host).toString()
    }

    fun getHostFromJson(json: String): List<HostJson> {
        return gson.fromJson(json, object : TypeToken<List<HostJson>>() {}.type)
    }
}
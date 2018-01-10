package org.b3log.zephyr.utils.model

/**
 * @author Zhang Yu
 * Date: 18年1月9日
 * Email: yu.zhang@7fresh.com
 */
data class HostJson(
        val enable : Boolean,
        val ip : String,
        val domain : String,
        val comment : String
)
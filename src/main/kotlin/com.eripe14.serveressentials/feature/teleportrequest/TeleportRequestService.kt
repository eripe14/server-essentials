package com.eripe14.serveressentials.feature.teleportrequest

import com.eripe14.serveressentials.config.PluginConfig
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import java.util.*
import java.util.concurrent.TimeUnit

class TeleportRequestService(
    private val config: PluginConfig
) {

    private val requests: Cache<UUID, UUID> = CacheBuilder
        .newBuilder()
        .expireAfterWrite(this.config.teleportExpireTime.toMillis(), TimeUnit.MILLISECONDS)
        .build()

    fun createRequest(sender: UUID, target: UUID) {
        this.requests.put(sender, target)
    }

    fun removeRequest(sender: UUID) {
        this.requests.asMap().remove(sender)
    }

    fun hasRequest(requester: UUID, target: UUID): Boolean {
        val map = this.requests.asMap()

        for (entry in map.entries) {
            if (entry.key == requester && entry.value == target) {
                return true
            }
        }

        return false
    }

}
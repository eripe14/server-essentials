package com.eripe14.serveressentials.feature.teleport

import org.bukkit.Location
import java.time.Duration
import java.time.Instant
import java.util.*
import java.util.concurrent.CompletableFuture

class TeleportService() {

    private val teleports: MutableMap<UUID, Teleport> = mutableMapOf()

    fun createTeleport(
        uuid: UUID,
        startLocation: Location,
        destinationLocation: Location,
        duration: Duration
    ) : Teleport {
        val teleport = Teleport(
            uuid,
            startLocation,
            destinationLocation,
            Instant.now().plus(duration),
            CompletableFuture()
        )

        this.teleports[uuid] = teleport
        return teleport
    }

    fun removeTeleport(uuid: UUID) {
        this.teleports.remove(uuid)
    }

    fun getTeleports() : List<Teleport> {
        return this.teleports.values.toList()
    }

}
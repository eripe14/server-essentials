package com.eripe14.serveressentials.feature.teleport

import org.bukkit.Location
import java.time.Instant
import java.util.UUID
import java.util.concurrent.CompletableFuture

class Teleport(
    private val playerUniqueId: UUID,
    private val startLocation: Location,
    private val destinationLocation: Location,
    private val teleportMoment: Instant,
    private val result: CompletableFuture<TeleportResult>
) {

    fun getPlayerUniqueId(): UUID {
        return this.playerUniqueId
    }

    fun getStartLocation(): Location {
        return this.startLocation
    }

    fun getDestinationLocation(): Location {
        return this.destinationLocation
    }

    fun getTeleportMoment(): Instant {
        return this.teleportMoment
    }

    fun getResult(): CompletableFuture<TeleportResult> {
        return this.result
    }

    fun completeResult(result: TeleportResult) {
        if (this.result.isDone) {
            throw IllegalStateException("Teleport result already completed");
        }

        this.result.complete(result)
    }

}
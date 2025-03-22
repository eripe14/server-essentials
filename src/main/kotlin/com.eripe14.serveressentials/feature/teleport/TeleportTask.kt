package com.eripe14.serveressentials.feature.teleport

import com.eripe14.serveressentials.config.MessageConfig
import com.eripe14.serveressentials.notice.NoticeService
import com.eripe14.serveressentials.util.TimeUtil
import org.bukkit.Location
import org.bukkit.Server
import org.bukkit.entity.Player
import java.time.Duration
import java.time.Instant


class TeleportTask(
    private val noticeService: NoticeService,
    private val teleportService: TeleportService,
    private val server: Server
) : Runnable {

    private val secondsOffset: Int = 1

    override fun run() {
        val teleports = this.teleportService.getTeleports()

        for (teleport in teleports) {
            val playerUniqueId = teleport.getPlayerUniqueId()
            val player = this.server.getPlayer(playerUniqueId)

            if (player == null) {
                this.teleportService.removeTeleport(playerUniqueId)
                continue
            }

            if (this.hasPlayerMovedDuringTeleport(player, teleport)) {
                this.teleportService.removeTeleport(playerUniqueId)
                teleport.completeResult(TeleportResult.MOVED_DURING_TELEPORT)

                this.noticeService.create()
                    .notice { messages -> messages.teleportCancelledMessage }
                    .player(playerUniqueId)
                    .send();

                continue
            }

            val now = Instant.now()
            val teleportMoment = teleport.getTeleportMoment()

            if (now.isBefore(teleportMoment)) {
                val between = Duration.between(now, teleportMoment)

                this.noticeService.create()
                    .notice { messages -> messages.teleportMessage }
                    .placeholder("{duration}", TimeUtil.format(between))
                    .player(playerUniqueId)
                    .send();
                continue
            }

            this.completeTeleport(player, teleport)
        }
    }

    private fun completeTeleport(player: Player, teleport: Teleport) {
        val destinationLocation: Location = teleport.getDestinationLocation()
        val uuid = teleport.getPlayerUniqueId()

        player.teleportAsync(destinationLocation)
        this.teleportService.removeTeleport(uuid)

        teleport.completeResult(TeleportResult.SUCCESS)

        this.noticeService.create()
            .notice { messages -> messages.teleportedMessage }
            .player(player.uniqueId)
            .send()
    }

    private fun hasPlayerMovedDuringTeleport(player: Player, teleport: Teleport): Boolean {
        val startLocation: Location = teleport.getStartLocation()

        return player.location.distance(startLocation) > 0.5
    }

}
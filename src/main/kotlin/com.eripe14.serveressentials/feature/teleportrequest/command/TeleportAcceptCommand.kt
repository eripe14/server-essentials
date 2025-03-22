package com.eripe14.serveressentials.feature.teleportrequest.command

import com.eripe14.serveressentials.config.PluginConfig
import com.eripe14.serveressentials.feature.teleport.TeleportService
import com.eripe14.serveressentials.feature.teleportrequest.TeleportRequestService
import com.eripe14.serveressentials.notice.NoticeService
import dev.rollczi.litecommands.annotations.argument.Arg
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import org.bukkit.entity.Player

@Command(name = "tpaccept")
class TeleportAcceptCommand(
    private val teleportRequestService: TeleportRequestService,
    private val teleportService: TeleportService,
    private val noticeService: NoticeService,
    private val pluginConfig: PluginConfig
) {

    @Execute
    @Permission("serveressentials.teleport.accept")
    fun execute(@Context player: Player, @Arg target: Player) {
        if (!this.teleportRequestService.hasRequest(target.uniqueId, player.uniqueId)) {
            this.noticeService.create()
                .notice { messages -> messages.teleportNoRequestMessage}
                .player(player.uniqueId)
                .placeholder("{player}", target.name)
                .send();
            return
        }

        this.teleportService.createTeleport(
            target.uniqueId,
            target.location,
            player.location,
            this.pluginConfig.teleportDuration
        )

        this.teleportRequestService.removeRequest(target.uniqueId)

        this.noticeService.create()
            .notice { messages -> messages.teleportAcceptMessage}
            .player(player.uniqueId)
            .placeholder("{player}", target.name)
            .send();

        this.noticeService.create()
            .notice { messages -> messages.teleportAcceptRequestMessage}
            .player(target.uniqueId)
            .placeholder("{player}", player.name)
            .send();
    }

}
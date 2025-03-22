package com.eripe14.serveressentials.feature.teleportrequest.command

import com.eripe14.serveressentials.config.PluginConfig
import com.eripe14.serveressentials.feature.teleportrequest.TeleportRequestService
import com.eripe14.serveressentials.notice.NoticeService
import dev.rollczi.litecommands.annotations.argument.Arg
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import org.bukkit.entity.Player

@Command(name = "tpa")
class TeleportRequestCommand(
    private val teleportRequestService: TeleportRequestService,
    private val noticeService: NoticeService,
    private val pluginConfig: PluginConfig
) {

    @Execute
    @Permission("serveressentials.teleport.request")
    fun execute(@Context player: Player, @Arg target: Player) {
        if (this.teleportRequestService.hasRequest(player.uniqueId, target.uniqueId)) {
            this.noticeService.create()
                .notice { messages -> messages.teleportAlreadySendRequestMessage }
                .player(player.uniqueId)
                .placeholder("{player}", target.name)
                .send()
            return
        }

        this.teleportRequestService.createRequest(player.uniqueId, target.uniqueId)

        this.noticeService.create()
            .notice { messages -> messages.teleportSendRequestMessage }
            .player(player.uniqueId)
            .placeholder("{player}", target.name)
            .send()

        this.noticeService.create()
            .notice { messages -> messages.teleportReceiveRequestMessage }
            .player(target.uniqueId)
            .placeholder("{player}", player.name)
            .send()

    }

}
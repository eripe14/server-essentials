package com.eripe14.serveressentials.feature.common

import com.eripe14.serveressentials.notice.NoticeService
import dev.rollczi.litecommands.annotations.argument.Arg
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import org.bukkit.entity.Player

@Command(name = "god")
class GodCommand(
    private val noticeService: NoticeService
) {

    @Execute
    @Permission("serveressentials.god")
    fun execute(@Context player: Player) {
        player.isInvulnerable = !player.isInvulnerable

        this.noticeService.create()
            .notice { messages ->
                if (player.isInvulnerable) messages.godModeEnableMessage
                else messages.godModeDisableMessage
            }
            .player(player.uniqueId)
            .send()
    }

    @Execute
    @Permission("serveressentials.god.others")
    fun execute(@Context player: Player, @Arg target: Player) {
        target.isInvulnerable = !target.isInvulnerable

        this.noticeService.create()
            .notice { messages ->
                if (target.isInvulnerable) messages.godModeEnableMessage
                else messages.godModeDisableMessage
            }
            .player(target.uniqueId)
            .send()

        this.noticeService.create()
            .notice { messages ->
                if (target.isInvulnerable) messages.godModeEnableOtherMessage
                else messages.godModeDisableOtherMessage
            }
            .placeholder("{player}", target.name)
            .player(player.uniqueId)
            .send()
    }

}
package com.eripe14.serveressentials.feature.common

import com.eripe14.serveressentials.notice.NoticeService
import dev.rollczi.litecommands.annotations.argument.Arg
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import org.bukkit.GameMode
import org.bukkit.entity.Player
import java.util.*

@Command(name = "gamemode")
class GameModeCommand(
    private val noticeService: NoticeService
) {

    @Execute
    @Permission("serveressentials.gamemode")
    fun execute(@Context player: Player, @Arg gameMode: GameMode) {
        player.gameMode = gameMode

        this.noticeService.create()
            .notice { messages -> messages.gameModeMessage}
            .placeholder("{gamemode}", gameMode.name.lowercase(Locale.getDefault()))
            .player(player.uniqueId)
            .send()
    }

    @Execute
    @Permission("serveressentials.gamemode.others")
    fun execute(@Context player: Player, @Arg gameMode: GameMode, @Arg target: Player) {
        target.gameMode = gameMode

        this.noticeService.create()
            .notice { messages -> messages.gameModeMessage}
            .placeholder("{gamemode}", gameMode.name.lowercase(Locale.getDefault()))
            .player(target.uniqueId)
            .send()

        this.noticeService.create()
            .notice { messages -> messages.gameModeSetMessage }
            .placeholder("{player}", target.name)
            .placeholder("{gamemode}", gameMode.name.lowercase(Locale.getDefault()))
            .player(player.uniqueId)
            .send()
    }

}
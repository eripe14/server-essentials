package com.eripe14.serveressentials.feature.common

import dev.rollczi.litecommands.annotations.argument.Arg
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import org.bukkit.entity.Player

@Command(name = "enderchest")
class EnderChestCommand {

    @Execute
    @Permission("serveressentials.enderchest")
    fun execute(@Context player: Player) {
        player.openInventory(player.enderChest)
    }

    @Execute
    @Permission("serveressentials.enderchest.others")
    fun execute(@Context player: Player, @Arg target: Player) {
        player.openInventory(target.enderChest)
    }

}
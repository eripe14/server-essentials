package com.eripe14.serveressentials.feature.invsee

import dev.rollczi.litecommands.annotations.argument.Arg
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import org.bukkit.entity.Player

@Command(name = "invsee")
class InvSeeCommand(
    private val invSeeService: InvSeeService
) {

    @Execute
    fun execute(@Context player: Player, @Arg target: Player) {
        player.openInventory(target.inventory) ?: return
        this.invSeeService.addViewer(player.uniqueId, target.uniqueId)
    }

}
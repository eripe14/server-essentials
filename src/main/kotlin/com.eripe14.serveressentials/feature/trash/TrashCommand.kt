package com.eripe14.serveressentials.feature.trash

import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import org.bukkit.entity.Player

@Command(name = "trash")
class TrashCommand(
    private val trashGui: TrashGui
) {

    @Execute
    @Permission("serveressentials.trash")
    fun execute(@Context player: Player) {
        this.trashGui.open(player)
    }

}
package com.eripe14.serveressentials.feature.trash

import com.eripe14.serveressentials.config.PluginConfig
import com.eripe14.serveressentials.notice.adventure.Legacy
import com.eripe14.serveressentials.util.ItemTransformer
import dev.triumphteam.gui.guis.Gui
import org.bukkit.entity.Player

class TrashGui(
    private val config: PluginConfig
) {

    fun open(player: Player) {
        val gui: Gui = Gui.gui()
            .title(Legacy.title(this.config.trashGuiTitle))
            .rows(this.config.trashGuiRows)
            .create()

        val quitItem = ItemTransformer.transformItem(this.config.trashGuiQuitItem) {
            it.isCancelled = true
            gui.close(player)
        }

        gui.setItem(this.config.trashGuiQuitItem.getItemSlot(), quitItem)
        gui.open(player)
    }

}
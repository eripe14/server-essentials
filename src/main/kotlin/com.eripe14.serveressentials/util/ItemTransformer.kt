package com.eripe14.serveressentials.util

import com.eripe14.serveressentials.config.contextual.ItemContextual
import com.eripe14.serveressentials.notice.adventure.Legacy
import com.eripe14.serveressentials.notice.adventure.LegacyColorProcessor
import dev.triumphteam.gui.builder.item.ItemBuilder
import dev.triumphteam.gui.components.GuiAction
import dev.triumphteam.gui.guis.GuiItem
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemFlag

object ItemTransformer {

    private val miniMessage: MiniMessage = MiniMessage.builder()
        .postProcessor(LegacyColorProcessor())
        .build()

    fun transformItem(item: ItemContextual, guiAction: GuiAction<InventoryClickEvent>): GuiItem {
        val itemFlags: Array<ItemFlag> = item.getItemFlags().toTypedArray()

        return ItemBuilder.from(item.getItemMaterial())
            .name(Legacy.RESET_ITALIC.append(this.miniMessage.deserialize(item.getItemName())))
            .lore(item.getItemLore().map { Legacy.RESET_ITALIC.append(this.miniMessage.deserialize(it)) })
            .amount(item.getItemAmount())
            .flags(*itemFlags)
            .asGuiItem(guiAction)
    }

}
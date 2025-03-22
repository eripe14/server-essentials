package com.eripe14.serveressentials.config

import com.eripe14.serveressentials.config.contextual.ItemContextual
import eu.okaeri.configs.OkaeriConfig
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import java.time.Duration

class PluginConfig : OkaeriConfig() {

    var trashGuiTitle: String = "Trash"

    var trashGuiRows: Int = 3

    var trashGuiQuitItem: ItemContextual = ItemContextual(
        22,
        Material.BARRIER,
        "&c&lQuit",
        listOf("&7Click to close the trash"),
        1,
        listOf(ItemFlag.HIDE_ATTRIBUTES)
    )

    var teleportExpireTime: Duration = Duration.ofSeconds(10)

    var teleportDuration: Duration = Duration.ofSeconds(5)

}
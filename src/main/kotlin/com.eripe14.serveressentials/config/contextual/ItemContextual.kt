package com.eripe14.serveressentials.config.contextual

import org.bukkit.Material
import org.bukkit.inventory.ItemFlag

class ItemContextual(
    private val itemSlot: Int,
    private val itemMaterial: Material,
    private val itemName: String,
    private val itemLore: List<String>,
    private val itemAmount: Int,
    private val itemFlags: List<ItemFlag>
) {

    fun getItemSlot(): Int {
        return itemSlot
    }

    fun getItemMaterial(): Material {
        return itemMaterial
    }

    fun getItemName(): String {
        return itemName
    }

    fun getItemLore(): List<String> {
        return itemLore
    }

    fun getItemAmount(): Int {
        return itemAmount
    }

    fun getItemFlags(): List<ItemFlag> {
        return itemFlags
    }

}
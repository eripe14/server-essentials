package com.eripe14.serveressentials.config.contextual

import eu.okaeri.configs.schema.GenericsDeclaration
import eu.okaeri.configs.serdes.DeserializationData
import eu.okaeri.configs.serdes.ObjectSerializer
import eu.okaeri.configs.serdes.SerializationData
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag

class ItemSerializer : ObjectSerializer<ItemContextual> {

    override fun supports(type: Class<in ItemContextual>): Boolean {
        return ItemContextual::class.java.isAssignableFrom(type)
    }

    override fun serialize(item: ItemContextual, data: SerializationData, generics: GenericsDeclaration) {
        data.add("itemSlot", item.getItemSlot())
        data.add("itemMaterial", item.getItemMaterial())
        data.add("itemName", item.getItemName())
        data.add("itemLore", item.getItemLore())
        data.add("itemAmount", item.getItemAmount())
        data.add("itemFlags", item.getItemFlags())
    }

    override fun deserialize(data: DeserializationData, generics: GenericsDeclaration): ItemContextual {
        return ItemContextual(
            data.get("itemSlot", Int::class.java),
            data.get("itemMaterial", Material::class.java),
            data.get("itemName", String::class.java),
            data.getAsList("itemLore", String::class.java),
            data.get("itemAmount", Int::class.java),
            data.getAsList("itemFlags", ItemFlag::class.java)
        )
    }
}
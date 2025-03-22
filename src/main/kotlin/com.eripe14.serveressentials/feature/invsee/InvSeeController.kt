package com.eripe14.serveressentials.feature.invsee

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.plugin.Plugin
import java.util.UUID

class InvSeeController(
    private val plugin: Plugin,
    private val invSeeService: InvSeeService
) : Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        handleInventoryInteraction(event.whoClicked as? Player ?: return)
    }

    @EventHandler
    fun onInventoryDrag(event: InventoryDragEvent) {
        handleInventoryInteraction(event.whoClicked as? Player ?: return)
    }

    private fun handleInventoryInteraction(viewer: Player) {
        val viewerUUID = viewer.uniqueId

        if (!invSeeService.isViewing(viewerUUID)) {
            return
        }

        val targetUUID = invSeeService.getTarget(viewerUUID) ?: return
        val target = Bukkit.getPlayer(targetUUID)

        if (target == null || !target.isOnline) {
            handleOfflineTarget(viewer, viewerUUID)
            return
        }

        // Synchronizacja - upewnij się, że zmiany są widoczne dla docelowego gracza
        Bukkit.getScheduler().runTask(plugin, Runnable { target.updateInventory() })
    }

    private fun handleOfflineTarget(viewer: Player, viewerUUID: UUID) {
        viewer.closeInventory()
        invSeeService.removeViewer(viewerUUID)
        viewer.sendMessage("§cGracz wyszedł z gry!")
    }

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        val viewer = event.player as? Player ?: return
        val viewerUUID = viewer.uniqueId

        if (!invSeeService.isViewing(viewerUUID)) {
            return
        }

        val targetUUID = invSeeService.getTarget(viewerUUID) ?: return
        invSeeService.removeViewer(viewerUUID)

        val target = Bukkit.getPlayer(targetUUID)
        if (target != null && target.isOnline) {
            target.sendMessage("§e${viewer.name} §aprzestał przeglądać twój ekwipunek!")
            target.updateInventory()
        }

        viewer.sendMessage("§aZamknięto podgląd ekwipunku.")
    }
}
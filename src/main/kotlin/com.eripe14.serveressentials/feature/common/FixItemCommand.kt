package com.eripe14.serveressentials.feature.common

import com.eripe14.serveressentials.notice.NoticeService
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.Repairable

@Command(name = "fix")
class FixItemCommand(
    private val noticeService: NoticeService
) {

    @Execute
    @Permission("serveressentials.fix")
    fun execute(@Context player: Player) {
        val inventory = player.inventory
        val item = inventory.getItem(inventory.heldItemSlot)
        val itemMeta = item?.itemMeta

        if (item == null || itemMeta !is Repairable) {
            this.noticeService.create()
                .notice { messages -> messages.fixItemNoItemMessage }
                .player(player.uniqueId)
                .send()
            return
        }

        if (itemMeta !is Damageable || itemMeta.damage == 0) {
            this.noticeService.create()
                .notice { messages -> messages.fixItemNoDamagedMessage }
                .player(player.uniqueId)
                .send()
            return
        }

        itemMeta.damage = 0
        item.itemMeta = itemMeta

        this.noticeService.create()
            .notice { messages -> messages.fixItemMessage }
            .player(player.uniqueId)
            .send()
    }

}
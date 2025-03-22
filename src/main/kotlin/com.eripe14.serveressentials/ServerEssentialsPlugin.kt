package com.eripe14.serveressentials

import com.eripe14.serveressentials.config.ConfigManager
import com.eripe14.serveressentials.config.MessageConfig
import com.eripe14.serveressentials.config.PluginConfig
import com.eripe14.serveressentials.feature.common.EnderChestCommand
import com.eripe14.serveressentials.feature.common.FixItemCommand
import com.eripe14.serveressentials.feature.common.GameModeCommand
import com.eripe14.serveressentials.feature.common.GodCommand
import com.eripe14.serveressentials.feature.invsee.InvSeeCommand
import com.eripe14.serveressentials.feature.invsee.InvSeeController
import com.eripe14.serveressentials.feature.invsee.InvSeeService
import com.eripe14.serveressentials.feature.teleport.TeleportService
import com.eripe14.serveressentials.feature.teleport.TeleportTask
import com.eripe14.serveressentials.feature.teleportrequest.TeleportRequestService
import com.eripe14.serveressentials.feature.teleportrequest.command.TeleportAcceptCommand
import com.eripe14.serveressentials.feature.teleportrequest.command.TeleportRequestCommand
import com.eripe14.serveressentials.feature.trash.TrashCommand
import com.eripe14.serveressentials.feature.trash.TrashGui
import com.eripe14.serveressentials.notice.NoticeHandler
import com.eripe14.serveressentials.notice.NoticeService
import com.eripe14.serveressentials.notice.adventure.LegacyColorProcessor
import com.eternalcode.multification.notice.Notice
import dev.rollczi.litecommands.LiteCommands
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory
import net.kyori.adventure.platform.AudienceProvider
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Server
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

class ServerEssentialsPlugin : JavaPlugin() {

    private lateinit var audienceProvider: AudienceProvider
    private lateinit var miniMessage: MiniMessage
    private lateinit var noticeService: NoticeService

    private lateinit var configManager: ConfigManager
    private lateinit var pluginConfig: PluginConfig
    private lateinit var messageConfig: MessageConfig

    private lateinit var liteCommands: LiteCommands<CommandSender>

    override fun onEnable() {
        val server: Server = this.server

        this.messageConfig = MessageConfig()

        this.audienceProvider = BukkitAudiences.create(this)
        this.miniMessage = MiniMessage.builder()
            .postProcessor(LegacyColorProcessor())
            .build()
        this.noticeService = NoticeService(this.messageConfig, this.audienceProvider, this.miniMessage)

        this.configManager = ConfigManager(this.noticeService.noticeRegistry)
        this.pluginConfig  = this.configManager.loadConfig(PluginConfig::class, this.dataFolder, "config.yml") as PluginConfig
        this.messageConfig = this.configManager.loadConfig(MessageConfig::class, this.dataFolder, "messages.yml") as MessageConfig

        val invSeeService = InvSeeService()

        val teleportService = TeleportService()
        val teleportRequestService = TeleportRequestService(this.pluginConfig)

        server.pluginManager.registerEvents(InvSeeController(this, invSeeService), this)
        server.scheduler.runTaskTimer(
            this,
            TeleportTask(this.noticeService, teleportService, server),
            20L,
            20L
        )

        this.liteCommands = LiteBukkitFactory.builder("serveressentials", this)
            .commands(
                EnderChestCommand(),
                FixItemCommand(this.noticeService),
                GameModeCommand(this.noticeService),
                GodCommand(this.noticeService),
                InvSeeCommand(invSeeService),
                TrashCommand(TrashGui(this.pluginConfig)),
                TeleportRequestCommand(teleportRequestService, this.noticeService, this.pluginConfig),
                TeleportAcceptCommand(teleportRequestService, teleportService, this.noticeService, this.pluginConfig)

            )
            .result(Notice::class.java, NoticeHandler(this.noticeService))
            .build()
    }

}
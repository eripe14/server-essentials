package com.eripe14.serveressentials.config

import com.eternalcode.multification.notice.Notice
import eu.okaeri.configs.OkaeriConfig

class MessageConfig : OkaeriConfig() {

    var gameModeMessage: Notice = Notice.chat(
        "<color:#22DD22>✔</color> <color:#00FF7F>Gamemode is set to: <color:#FFFF55>{gamemode}</color>!</color>"
    )

    var gameModeSetMessage: Notice = Notice.chat(
        "<color:#22DD22>✔</color> <color:#00FF7F>Gamemode for <color:#FFFF55>{player}</color>" +
                " now is set to: <color:#FFFF55>{gamemode}</color>!</color>"
    )

    var godModeEnableMessage: Notice = Notice.chat(
        "<color:#22DD22>✔</color> <color:#00FF7F>Godmode is now enabled!</color>"
    )

    var godModeDisableMessage: Notice = Notice.chat(
        "<color:#22DD22>✔</color> <color:#00FF7F>Godmode is now disabled!</color>"
    )

    var godModeEnableOtherMessage: Notice = Notice.chat(
        "<color:#22DD22>✔</color> <color:#00FF7F>Godmode for <color:#FFFF55>{player}</color> is now enabled!</color>"
    )

    var godModeDisableOtherMessage: Notice = Notice.chat(
        "<color:#22DD22>✔</color> <color:#00FF7F>Godmode for <color:#FFFF55>{player}</color> is now disabled!</color>"
    )

    var fixItemNoItemMessage: Notice = Notice.chat(
        "<color:#FF2222>❌</color> <color:#FF4444>You have to hold an item in your hand!</color>"
    )

    var fixItemNoDamagedMessage: Notice = Notice.chat(
        "<color:#FF2222>❌</color> <color:#FF4444>Item in your hand is not damaged!</color>"
    )

    var fixItemMessage: Notice = Notice.chat(
        "<color:#22DD22>✔</color> <color:#00FF7F>Item in your hand is now fixed!</color>"
    )

    var teleportSendRequestMessage: Notice = Notice.chat(
        "<color:#22DD22>✔</color> <color:#00FF7F>Teleport request has been sent to <color:#FFFF55>{player}</color>!</color>"
    )

    var teleportAlreadySendRequestMessage: Notice = Notice.chat(
        "<color:#FF2222>❌</color> <color:#FF4444>You have already sent a teleport request to <color:#FFAA00>{player}</color>!</color>"
    )

    var teleportReceiveRequestMessage: Notice = Notice.chat(
        "<color:#22DD22>✔</color> <color:#00FF7F>You have received a teleport request from <color:#FFFF55>{player}</color>!</color>"
    )

    var teleportCancelledMessage: Notice = Notice.actionbar(
        "<color:#FF2222>❌</color> <color:#FF4444>You moved! Teleport cancelled!</color>"
    )

    var teleportMessage: Notice = Notice.actionbar(
        "<color:#00AAFF>You will be teleported in <color:#FFD700>{duration}</color>.</color>"
    )

    var teleportedMessage: Notice = Notice.chat(
        "<color:#22DD22>✔</color> <color:#00FF7F>You have been teleported!</color>"
    )

    var teleportNoRequestMessage: Notice = Notice.chat(
        "<color:#FF2222>❌</color> <color:#FF4444>You don't have any teleport requests from <color:#FFAA00>{player}</color>!</color>"
    )

    var teleportAcceptMessage: Notice = Notice.chat(
        "<color:#22DD22>✔</color> <color:#00FF7F>You have accepted teleport request from <color:#FFFF55>{player}</color>!</color>"
    )

    var teleportAcceptRequestMessage: Notice = Notice.chat(
        "<color:#22DD22>✔</color> <color:#00FF7F><color:#FFFF55>{player}</color> has accepted your teleport request!</color>"
    )

}
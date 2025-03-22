package com.eripe14.serveressentials.feature.invsee

import java.util.UUID

class InvSeeService {

    private val activeViewers: MutableMap<UUID, UUID> = mutableMapOf()

    fun addViewer(viewer: UUID, target: UUID) {
        this.activeViewers[viewer] = target
    }

    fun removeViewer(viewer: UUID) {
        this.activeViewers.remove(viewer)
    }

    fun isViewing(viewer: UUID): Boolean {
        return this.activeViewers.containsKey(viewer)
    }

    fun getTarget(viewer: UUID): UUID? {
        return this.activeViewers[viewer]
    }

}
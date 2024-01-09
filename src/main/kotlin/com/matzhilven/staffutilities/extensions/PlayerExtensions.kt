package com.matzhilven.staffutilities.extensions

import org.bukkit.OfflinePlayer

val OfflinePlayer.banned: Boolean
    get() = this.isBanned

val OfflinePlayer.muted: Boolean
    get() = false

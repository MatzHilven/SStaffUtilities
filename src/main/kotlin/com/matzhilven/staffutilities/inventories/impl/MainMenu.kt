package com.matzhilven.staffutilities.inventories.impl

import com.matzhilven.staffutilities.StaffUtilities
import com.matzhilven.staffutilities.extensions.send
import com.matzhilven.staffutilities.inventories.Menu
import com.matzhilven.staffutilities.inventories.MenuItem
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

class MainMenu(
    main: StaffUtilities,
    player: Player,
    target: OfflinePlayer,
) : Menu(main, player, target) {
    override fun namePlaceholders() = mapOf("name" to (target?.name ?: "Unknown"))
    
    init {
        slots["player-head"] = MenuItem()
            .onClick { event ->
                "&aYou clicked on ${event.currentItem?.itemMeta?.displayName}!".send(player)
            }
            .placeholders(
                mapOf(
                    "name" to target.name,
                    "status" to target.isOnline.let { if (it) "&aOnline" else "&cOffline" },
                    "banned" to target.isBanned.let { if (it) "&cBanned" else "&aNot Banned" },
                    "muted" to target.isBanned.let { if (it) "&cMuted" else "&aNot Muted" },
                )
            )
    }
}

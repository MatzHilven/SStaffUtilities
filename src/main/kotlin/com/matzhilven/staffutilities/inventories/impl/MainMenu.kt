package com.matzhilven.staffutilities.inventories.impl

import com.matzhilven.staffutilities.StaffUtilities
import com.matzhilven.staffutilities.extensions.send
import com.matzhilven.staffutilities.inventories.Menu
import com.matzhilven.staffutilities.inventories.MenuItem
import org.bukkit.entity.Player

class MainMenu(
    main: StaffUtilities,
    player: Player,
) : Menu(main, player) {
    override fun namePlaceholders() = mapOf("name" to player.name)
    
    init {
        slots["player-head"] = MenuItem()
            .onClick { event ->
                "&aYou clicked on ${event.currentItem?.itemMeta?.displayName}!".send(player)
            }
    }
}

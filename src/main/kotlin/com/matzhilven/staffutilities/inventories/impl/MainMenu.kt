package com.matzhilven.staffutilities.inventories.impl

import com.matzhilven.staffutilities.StaffUtilities
import com.matzhilven.staffutilities.inventories.Menu
import org.bukkit.entity.Player

class MainMenu(
    main: StaffUtilities,
    player: Player,
) : Menu(main, player) {
    override fun namePlaceholders() = mapOf("name" to player.name)
    
}

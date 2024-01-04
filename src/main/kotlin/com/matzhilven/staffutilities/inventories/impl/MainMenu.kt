package com.matzhilven.staffutilities.inventories.impl

import com.matzhilven.staffutilities.StaffUtilities
import com.matzhilven.staffutilities.inventories.Menu
import com.matzhilven.staffutilities.utils.Config
import org.bukkit.entity.Player

class MainMenu(
    player: Player,
    previous: Menu? = null,
) : Menu(player, previous) {
    
    init {
//        config = Config("menus/main.yml")
    }
    
    override fun setItems() {
        TODO("Not yet implemented")
    }
}

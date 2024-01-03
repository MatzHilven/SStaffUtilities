package com.matzhilven.staffutilities.inventories

import org.bukkit.entity.Player

abstract class PaginatedMenu(
    player: Player,
    previous: Menu? = null
) : Menu(player, previous) {
    
    var page: Int = 0
    var index: Int = 0
    
    fun getMaxItemsPerPage() = getSlots() - 18
}

package com.matzhilven.staffutilities.inventories

import com.matzhilven.staffutilities.StaffUtilities
import org.bukkit.entity.Player

abstract class PaginatedMenu(
    main: StaffUtilities,
    player: Player,
    previous: Menu? = null
) : Menu(main, player, previous) {
    
    var page: Int = 0
    var index: Int = 0
    
    fun getMaxItemsPerPage() = getSlots() - 18
}

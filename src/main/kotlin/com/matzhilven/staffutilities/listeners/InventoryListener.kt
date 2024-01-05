package com.matzhilven.staffutilities.listeners

import com.matzhilven.staffutilities.StaffUtilities
import com.matzhilven.staffutilities.inventories.Menu
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class InventoryListener(
    val main: StaffUtilities
) : Listener {
    
    init {
        main.server.pluginManager.registerEvents(this, main)
    }
    
    @EventHandler
    fun onMenuClick(event: InventoryClickEvent) {
        val holder = event.clickedInventory.holder
        if (holder !is Menu) return
        holder.handleClick(event)
    }
    
    @EventHandler
    fun onMenuClose(event: InventoryCloseEvent) {
        val holder = event.inventory.holder
        if (holder !is Menu) return
        holder.handleClose(event)
    }
}

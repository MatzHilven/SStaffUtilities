package com.matzhilven.staffutilities.inventories

import org.bukkit.event.inventory.InventoryClickEvent

class MenuItem {
    
    private val placeholderMap = mutableMapOf<String, String>()
    private var clickAction: ((InventoryClickEvent) -> Unit)? = null
    
    fun placeholders(map: Map<String, String>): MenuItem {
        placeholderMap.clear()
        placeholderMap.putAll(map)
        return this
    }
    
    fun onClick(action: (InventoryClickEvent) -> Unit): MenuItem {
        clickAction = action
        return this
    }
    
    fun executeOnClick(event: InventoryClickEvent) {
        clickAction?.invoke(event)
    }
    
    fun getPlaceholders(): Map<String, String> {
        return placeholderMap.toMap()
    }
    
}

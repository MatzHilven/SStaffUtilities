package com.matzhilven.staffutilities.inventories

import com.matzhilven.staffutilities.utils.Config
import com.matzhilven.staffutilities.utils.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

abstract class Menu(
    val player: Player,
    val previous: Menu? = null
) : InventoryHolder {
    
    var config: Config? = null
    
    private val inv: Inventory by lazy {
        Bukkit.createInventory(this, getSlots(), getName())
    }
    
    private var slots: Map<Int, String> = HashMap()
    
    fun open() {
        setItems()
        player.openInventory(inv)
    }
    
    fun getName() = config!!.getString("name")
    
    fun getSlots() = config!!.getInt("size") * 9
    
    open fun handleClick(event: InventoryClickEvent) {}
    
    open fun handleClose(event: InventoryCloseEvent) {}
    
    abstract fun setItems()
    
    private fun setFillerGlass(glass: ItemStack, slots: List<Int>) {
        slots.forEach { inv.setItem(it, glass) }
    }
    
    private fun setFillerGlass(section: ConfigurationSection) {
        setFillerGlass(ItemBuilder.fromConfigSection(section).build(), section.getIntegerList("slot"))
    }
    
    override fun getInventory(): Inventory {
        return inv
    }
    
    
}

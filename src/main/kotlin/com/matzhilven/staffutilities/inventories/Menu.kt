package com.matzhilven.staffutilities.inventories

import com.matzhilven.staffutilities.StaffUtilities
import com.matzhilven.staffutilities.extensions.colorize
import com.matzhilven.staffutilities.extensions.replace
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
    main: StaffUtilities,
    val player: Player,
    val previous: Menu? = null
) : InventoryHolder {
    
    private var config: Config
    
    init {
        config = Config.getInstance(main, "menus/${javaClass.simpleName.removeSuffix("Menu").lowercase()}.yml")
    }
    
    private val inv: Inventory by lazy {
        Bukkit.createInventory(this, getSlots(), getName())
    }
    
    private val slotIDs: HashMap<Int, String> = HashMap()
    val slots: HashMap<String, MenuItem> = HashMap()
    
    fun open() {
        setItems()
        player.openInventory(inv)
    }
    
    private fun getName() = config.getString("name").replace(namePlaceholders()).colorize()
    
    fun getSlots() = config.getInt("size") * 9
    
    fun handleClick(event: InventoryClickEvent) {
        event.isCancelled = true
        slots[slotIDs[event.slot]]?.executeOnClick(event)
    }
    
    open fun handleClose(event: InventoryCloseEvent) {}
    
    private fun setItems() {
        config.getSection("items")?.let {
            it.getKeys(false).forEach { key ->
                val section = config.getSection("items.$key")!!
                val item = ItemBuilder.fromConfigSection(section).build()
                val slot = section.getInt("slot")
                inv.setItem(slot, item)
                slotIDs[slot] = key
            }
        }
        
        config.getSection("filler")?.let { setFillerGlass(it) }
    }
    
    private fun setFillerGlass(glass: ItemStack, slots: List<Int>) {
        slots.forEach { inv.setItem(it, glass) }
    }
    
    private fun setFillerGlass(section: ConfigurationSection) {
        setFillerGlass(ItemBuilder.fromConfigSection(section).build(), section.getIntegerList("slot"))
    }
    
    abstract fun namePlaceholders(): Map<String, String>
    
    override fun getInventory(): Inventory {
        return inv
    }
}

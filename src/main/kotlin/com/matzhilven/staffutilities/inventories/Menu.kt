package com.matzhilven.staffutilities.inventories

import com.matzhilven.staffutilities.StaffUtilities
import com.matzhilven.staffutilities.extensions.colorize
import com.matzhilven.staffutilities.extensions.owner
import com.matzhilven.staffutilities.extensions.replace
import com.matzhilven.staffutilities.extensions.replaceName
import com.matzhilven.staffutilities.utils.Config
import com.matzhilven.staffutilities.utils.fromConfigSection
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
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
    val target: OfflinePlayer? = null,
    private val previous: Menu? = null
) : InventoryHolder {
    
    private var config: Config
    private val slotIDs: HashMap<Int, String> = HashMap()
    private val inv: Inventory by lazy {
        Bukkit.createInventory(this, getSlots(), getName())
    }
    
    val slots: HashMap<String, MenuItem> = HashMap()
    
    init {
        config = Config.getInstance(main, "menus/${javaClass.simpleName.removeSuffix("Menu").lowercase()}.yml")
    }
    
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
    
    open fun handleClose(event: InventoryCloseEvent) {
        previous?.open()
    }
    
    private fun setItems() {
        config.getSection("items")?.let {
            it.getKeys(false).forEach { key ->
                val section = config.getSection("items.$key")!!
                val item = fromConfigSection(section)
                    .owner(target?.name ?: "")
                    .replace(slots[key]?.getPlaceholders() ?: HashMap())
                    .replaceName("name", "test")
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
        setFillerGlass(fromConfigSection(section), section.getIntegerList("slot"))
    }
    
    abstract fun namePlaceholders(): Map<String, String>
    
    override fun getInventory(): Inventory {
        return inv
    }
}

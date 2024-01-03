package com.matzhilven.staffutilities.utils

import com.matzhilven.staffutilities.extensions.colorize
import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

class ItemBuilder(
    private val item: ItemStack,
) {
    companion object {
        fun fromConfigSection(section: ConfigurationSection): ItemBuilder {
            if (section.contains("head")) return skullFromConfigSection(section)
            
            val material = Material.valueOf(section.getString("material").uppercase(Locale.getDefault()))
            
            val itemBuilder: ItemBuilder = if (material == Material.SKULL_ITEM) {
                fromSkull(section.getString("skull-data"))
            } else {
                ItemBuilder(
                    Material.valueOf(section.getString("material").uppercase(Locale.getDefault())),
                    section.getInt("amount", 1),
                    section.getInt("data", 0).toShort()
                )
            }
            return itemBuilder
                .setName(section.getString("name", ""))
                .setLore(section.getStringList("lore"))
                .addGlow(section.getBoolean("glow"))
        }
        
        fun fromSkull(value: String, uuid: UUID = UUID.randomUUID()): ItemBuilder {
            val head = ItemStack(Material.SKULL_ITEM, 1, 3.toShort())
            val meta = head.itemMeta as SkullMeta
//        val profile = GameProfile(uuid, null)
//        profile.getProperties().put("textures", Property("textures", value))
//        val profileField: Field
//        try {
//            profileField = meta.javaClass.getDeclaredField("profile")
//            profileField.isAccessible = true
//            profileField[meta] = profile
//        } catch (e: IllegalArgumentException) {
//            e.printStackTrace()
//        } catch (e: IllegalAccessException) {
//            e.printStackTrace()
//        } catch (e: NoSuchFieldException) {
//            e.printStackTrace()
//        } catch (e: SecurityException) {
//            e.printStackTrace()
//        }
            head.itemMeta = meta
            return ItemBuilder(head)
        }
        
        fun skullFromConfigSection(section: ConfigurationSection): ItemBuilder {
            return fromSkull(value = section.getString("head"))
                .setName(section.getString("name", ""))
                .setLore(section.getStringList("lore"))
                .addGlow(section.getBoolean("glow"))
        }
    }
    
    constructor(material: Material, amount: Int = 1, durability: Short) : this(ItemStack(material, amount, durability))
    
    fun setName(name: String): ItemBuilder {
        val meta = item.itemMeta
        meta.displayName = name.colorize()
        item.itemMeta = meta
        return this
    }
    
    fun setLore(lore: List<String>): ItemBuilder {
        val meta = item.itemMeta
        meta.lore = lore.colorize()
        item.itemMeta = meta
        return this
    }
    
    fun addLoreLine(line: String): ItemBuilder {
        val meta = item.itemMeta
        val lore = meta.lore
        lore!!.add(line.colorize())
        meta.lore = lore
        item.itemMeta = meta
        return this
    }
    
    fun addGlow(glow: Boolean): ItemBuilder {
        if (glow) {
            val meta = item.itemMeta
            meta.addEnchant(org.bukkit.enchantments.Enchantment.DURABILITY, 1, true)
            meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS)
            item.itemMeta = meta
        }
        return this
    }
    
    fun replaceLore(placeholder: String, value: String): ItemBuilder {
        val meta = item.itemMeta
        val lore = meta.lore
        for (i in lore!!.indices) {
            lore[i] = lore[i].replace(placeholder, value)
        }
        meta.lore = lore
        item.itemMeta = meta
        return this
    }
    
    fun replaceLore(placeholder: String, value: List<String>): ItemBuilder {
        val meta = item.itemMeta
        val lore = meta.lore
        for (i in lore!!.indices) {
            for (j in value.indices) {
                lore[i] = lore[i].replace(placeholder, value[j])
            }
        }
        meta.lore = lore
        item.itemMeta = meta
        return this
    }
    
    fun replaceName(placeholder: String, value: String): ItemBuilder {
        val meta = item.itemMeta
        meta.displayName = meta.displayName.replace(placeholder, value)
        item.itemMeta = meta
        return this
    }
    
    fun replaceName(placeholder: String, value: List<String>): ItemBuilder {
        val meta = item.itemMeta
        for (i in value.indices) {
            meta.displayName = meta.displayName.replace(placeholder, value[i])
        }
        item.itemMeta = meta
        return this
    }
    
    fun setOwner(owner: String): ItemBuilder {
        val meta = item.itemMeta as SkullMeta
        meta.owner = owner
        item.itemMeta = meta
        return this
    }
    
    fun setAmount(amount: Int): ItemBuilder {
        item.amount = amount
        return this
    }
    
    fun setDurability(durability: Short): ItemBuilder {
        item.durability = durability
        return this
    }
    
    fun build(): ItemStack {
        return item
    }
    
}

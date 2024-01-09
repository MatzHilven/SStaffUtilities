package com.matzhilven.staffutilities.utils

import com.matzhilven.staffutilities.extensions.glow
import com.matzhilven.staffutilities.extensions.lore
import com.matzhilven.staffutilities.extensions.name
import com.matzhilven.staffutilities.extensions.owner
import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.lang.reflect.Field
import java.util.*

fun fromConfigSection(
    section: ConfigurationSection, player: String = ""
): ItemStack {
    if (section.contains("head")) return skullFromConfigSection(section)
    if (section.contains("player-head") && section.getBoolean("player-head")) return playerHead(section, player)
    
    val material = Material.valueOf(section.getString("material").uppercase(Locale.getDefault()))
    
    val itemBuilder: ItemStack = if (material == Material.SKULL_ITEM) {
        fromSkull(section.getString("skull-data"))
    } else {
        ItemStack(
            Material.valueOf(section.getString("material").uppercase(Locale.getDefault())),
            section.getInt("amount", 1),
            section.getInt("data", 0).toShort()
        )
    }
    return itemBuilder.name(section.getString("name", "")).lore(section.getStringList("lore"))
        .glow(section.getBoolean("glow"))
}

fun fromSkull(value: String, uuid: UUID = UUID.randomUUID()): ItemStack {
    val head = ItemStack(Material.SKULL_ITEM, 1, 3.toShort())
    val meta = head.itemMeta as SkullMeta
    val profile = GameProfile(uuid, null)
    profile.properties.put("textures", Property("textures", value))
    val profileField: Field
    try {
        profileField = meta.javaClass.getDeclaredField("profile")
        profileField.isAccessible = true
        profileField[meta] = profile
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
    head.itemMeta = meta
    return head
}

fun skullFromConfigSection(section: ConfigurationSection, uuid: UUID = UUID.randomUUID()): ItemStack {
    return fromSkull(section.getString("head"), uuid).name(section.getString("name", ""))
        .lore(section.getStringList("lore")).glow(section.getBoolean("glow"))
}

fun playerHead(section: ConfigurationSection, player: String): ItemStack {
    return ItemStack(Material.SKULL_ITEM, 1, 3.toShort()).name(section.getString("name", ""))
        .lore(section.getStringList("lore")).glow(section.getBoolean("glow")).owner(player)
}


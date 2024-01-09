package com.matzhilven.staffutilities.extensions

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.SkullMeta
import java.util.*
import java.util.function.Consumer

fun ItemStack.amount(amount: Int): ItemStack {
    setAmount(amount)
    return this
}

fun ItemStack.name(name: String): ItemStack {
    val meta = itemMeta
    meta.displayName = name.colorize()
    itemMeta = meta
    return this
}

fun ItemStack.lore(text: String): ItemStack {
    val meta = itemMeta
    var lore: MutableList<String>? = meta.lore
    if (lore == null) {
        lore = ArrayList()
    }
    lore.add(text)
    meta.lore = lore.colorize()
    itemMeta = meta
    return this
}

fun ItemStack.lore(vararg text: String): ItemStack {
    Arrays.stream(text).forEach { this.lore(it) }
    return this
}

fun ItemStack.lore(text: List<String>): ItemStack {
    text.forEach { this.lore(it) }
    return this
}

fun ItemStack.durability(durability: Int): ItemStack {
    setDurability(durability.toShort())
    return this
}

fun ItemStack.enchantment(enchantment: Enchantment, level: Int): ItemStack {
    addUnsafeEnchantment(enchantment, level)
    return this
}

fun ItemStack.enchantment(enchantment: Enchantment): ItemStack {
    addUnsafeEnchantment(enchantment, 1)
    return this
}

fun ItemStack.type(material: Material): ItemStack {
    type = material
    return this
}

fun ItemStack.clearLore(): ItemStack {
    val meta = itemMeta
    meta.lore = ArrayList()
    itemMeta = meta
    return this
}

fun ItemStack.clearEnchantments(): ItemStack {
    enchantments.keys.forEach(Consumer { enchantment: Enchantment -> removeEnchantment(enchantment) })
    return this
}

fun ItemStack.color(color: Color): ItemStack {
    if (type == Material.LEATHER_BOOTS
        || type == Material.LEATHER_CHESTPLATE
        || type == Material.LEATHER_HELMET
        || type == Material.LEATHER_LEGGINGS
    ) {
        
        val meta = itemMeta as LeatherArmorMeta
        meta.color = color
        itemMeta = meta
        return this
    } else {
        throw IllegalArgumentException("Colors only applicable for leather armor!")
    }
}

fun ItemStack.flag(vararg flag: ItemFlag): ItemStack {
    val meta = itemMeta
    meta.addItemFlags(*flag)
    itemMeta = meta
    return this
}

fun ItemStack.replaceLore(placeholder: String, value: String): ItemStack {
    val meta = itemMeta
    val lore = meta.lore
    for (i in lore!!.indices) {
        lore[i] = lore[i].replace("%$placeholder%", value.colorize())
    }
    meta.lore = lore
    itemMeta = meta
    return this
}

fun ItemStack.replaceName(placeholder: String, value: String): ItemStack {
    val meta = itemMeta
    meta.displayName = meta.displayName.replace("%$placeholder%", value.colorize())
    itemMeta = meta
    return this
}

fun ItemStack.replace(placeholder: String, value: String): ItemStack {
    replaceName(placeholder, value)
    replaceLore(placeholder, value)
    return this
}

fun ItemStack.glow(glow: Boolean): ItemStack {
    if (glow) {
        val meta = itemMeta
        meta.addEnchant(Enchantment.DURABILITY, 1, true)
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        itemMeta = meta
    }
    return this
}

fun ItemStack.replace(placeholders: Map<String, String>): ItemStack {
    placeholders.forEach { (placeholder, value) -> replace(placeholder, value) }
    return this
}

fun ItemStack.owner(owner: String): ItemStack {
    val meta = itemMeta
    if (meta is SkullMeta) {
        meta.owner = owner
        itemMeta = meta
    }
    return this
}

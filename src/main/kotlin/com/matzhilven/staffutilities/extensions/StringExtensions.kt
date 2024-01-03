package com.matzhilven.staffutilities.extensions

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import java.util.*

fun String.colorize() = ChatColor.translateAlternateColorCodes('&', this)

fun String.send(sender: CommandSender) = sender.sendMessage(this.colorize())

fun List<String>.colorize() = this.map { ChatColor.translateAlternateColorCodes('&', it) }

fun List<String>.send(sender: CommandSender) = this.forEach { it.send(sender) }

fun <T : Enum<T>> format(enum: T) = enum.name.lowercase(Locale.getDefault()).replace("_", " ").replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

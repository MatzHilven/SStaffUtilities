package com.matzhilven.staffutilities.commands

import com.matzhilven.staffutilities.StaffUtilities
import com.matzhilven.staffutilities.inventories.impl.MainMenu
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PunishCommand(
    val main: StaffUtilities
) : CommandExecutor {
    
    init {
        main.getCommand("punish").executor = this
    }
    
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            MainMenu(main, sender).open()
        }
        
        return true
    }
}

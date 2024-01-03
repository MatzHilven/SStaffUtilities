package com.matzhilven.staffutilities;

import com.matzhilven.staffutilities.commands.PunishCommand
import org.bstats.bukkit.Metrics
import org.bukkit.plugin.java.JavaPlugin

class StaffUtilities : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        
        // Commands
        PunishCommand(this)
        
        // bStats
        Metrics(this, 20628)
    }
    
    override fun onDisable() {
        // Plugin shutdown logic
    }
    
    companion object {
        val instance: StaffUtilities = getPlugin(StaffUtilities::class.java)
    }
}


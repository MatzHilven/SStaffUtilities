package com.matzhilven.staffutilities;

import org.bstats.bukkit.Metrics
import org.bukkit.plugin.java.JavaPlugin

class StaffUtilities : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
    
        Metrics(this, 20628)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}


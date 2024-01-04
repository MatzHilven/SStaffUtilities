package com.matzhilven.staffutilities

import com.matzhilven.staffutilities.commands.PunishCommand
import com.matzhilven.staffutilities.punishment.AffectedUsers
import com.matzhilven.staffutilities.punishment.Punishments
import com.matzhilven.staffutilities.punishment.edit.Edits
import org.bstats.bukkit.Metrics
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class StaffUtilities : JavaPlugin() {
    
    override fun onEnable() {
        saveDefaultConfig()
        
        Database.connect(
            "jdbc:mysql://${config.getString("database.host")}:${config.getInt("database.port")}/${config.getString("database.database")}",
            driver = "com.mysql.cj.jdbc.Driver",
            user = config.getString("database.username"),
            password = config.getString("database.password") ?: ""
        )
        
        transaction {
            SchemaUtils.create(Punishments, AffectedUsers, Edits)
        }
        
        // Commands
        PunishCommand(this)
        
        // bStats
        Metrics(this, 20628)
    }
    
    override fun onDisable() {
        // Plugin shutdown logic
    }
}


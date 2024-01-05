package com.matzhilven.staffutilities.utils

import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File

class Config(
    val main: Plugin,
    val name: String,
) {
    
    private var file: File = File(main.dataFolder, name)
    private var config: YamlConfiguration
    
    init {
        if (!file.exists()) {
            file.parentFile.mkdirs()
            main.saveResource(name, false)
        }
        
        config = YamlConfiguration.loadConfiguration(file)
        
        try {
            config.load(file)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    fun save() {
        try {
            config.save(file)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    fun reload() {
        try {
            config.load(file)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    fun getString(path: String): String {
        return config.getString(path)!!
    }
    
    fun getInt(path: String): Int {
        return config.getInt(path)
    }
    
    fun getBoolean(path: String): Boolean {
        return config.getBoolean(path)
    }
    
    fun getDouble(path: String): Double {
        return config.getDouble(path)
    }
    
    fun getLong(path: String): Long {
        return config.getLong(path)
    }
    
    fun getStringList(path: String): List<String> {
        return config.getStringList(path)
    }
    
    fun getSection(path: String): ConfigurationSection? {
        return config.getConfigurationSection(path) ?: return null
    }
    
    fun set(path: String, value: Any) {
        config.set(path, value)
    }
    
    fun getConfig(): YamlConfiguration {
        return config
    }
}

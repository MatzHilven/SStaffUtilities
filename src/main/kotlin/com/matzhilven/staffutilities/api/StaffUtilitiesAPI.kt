package com.matzhilven.staffutilities.api

import com.matzhilven.staffutilities.punishment.Punishment
import org.bukkit.entity.Player

interface StaffUtilitiesAPI {
    
    fun isMuted(player: Player): Boolean
    
    fun isBanned(player: Player): Boolean
    
    fun getPunishments(player: Player): List<Punishment>
    
}

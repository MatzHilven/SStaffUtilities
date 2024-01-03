package com.matzhilven.staffutilities.punishment

data class PunishmentType(
    val configName: String,
    val displayName: String,
    val lore: List<String>,
    val order: Int,
    val type: String,
    val priority: Int,
    val severityLevels: Map<Int, SeverityLevel>
)

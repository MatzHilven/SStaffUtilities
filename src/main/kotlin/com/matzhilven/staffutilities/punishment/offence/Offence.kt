package com.matzhilven.staffutilities.punishment.offence

import com.matzhilven.staffutilities.punishment.SeverityLevel

data class Offence(
    val configName: String,
    val displayName: String,
    val lore: List<String>,
    val order: Int,
    val type: String,
    val priority: Int,
    val severityLevels: Map<Int, SeverityLevel>
)

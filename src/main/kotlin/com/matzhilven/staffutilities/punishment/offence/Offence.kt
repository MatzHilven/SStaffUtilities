package com.matzhilven.staffutilities.punishment.offence

import com.matzhilven.staffutilities.punishment.severity.OffenceLevel

data class Offence(
    // TODO: if this is only used for display items replace displayName & lore to a display item parameter
    val configName: String,
    val displayName: String,
    val lore: List<String>,
    val order: Int,
    val kind: OffenceKind,
    val priority: Int,
    val levels: Map<Int, OffenceLevel>
)

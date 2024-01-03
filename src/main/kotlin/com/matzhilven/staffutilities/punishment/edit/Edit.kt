package com.matzhilven.staffutilities.punishment.edit

import java.sql.Timestamp
import java.util.*

data class Edit(
    val id: Int,
    val staff: UUID,
    val timestamp: Timestamp,
    val previousReason: String? = null,
    val newReason: String = "",
    val previousEnd: Timestamp? = null,
    val newEnd: Timestamp = Timestamp(0),
    val previousAffectedPlayers: List<UUID>? = null,
    val newAffectedPlayers: List<UUID> = listOf(),
    val removed: Boolean = false
) {
    fun getType(): EditType {
        if (removed) return EditType.REMOVED
        if (previousReason != null) return EditType.REASON
        if (previousEnd != null && !previousEnd.equals(newEnd)) return EditType.DURATION
        return EditType.AFFECTED_PLAYERS
    }
}

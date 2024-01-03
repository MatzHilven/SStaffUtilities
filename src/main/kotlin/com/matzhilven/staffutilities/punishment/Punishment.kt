package com.matzhilven.staffutilities.punishment

import com.matzhilven.staffutilities.punishment.offence.Offence
import java.sql.Timestamp
import java.util.*

data class Punishment(
    val id: Int,
    val type: Offence,
    val punished: UUID,
    val punisher: UUID,
    val start: Timestamp,
    
    var affectedPlayers: List<UUID>,
    var permanent: Boolean,
    var reason: String,
    var removed: Boolean,
    var severityLevel: Int,
    var end: Timestamp
) {

}

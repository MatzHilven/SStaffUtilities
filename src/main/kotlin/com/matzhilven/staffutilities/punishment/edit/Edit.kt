package com.matzhilven.staffutilities.punishment.edit

import com.matzhilven.staffutilities.punishment.Punishment
import com.matzhilven.staffutilities.punishment.Punishments
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

class Edit(id: EntityID<Int>) : IntEntity(id) {
    
    companion object : IntEntityClass<Edit>(Edits)
    
    var punishment by Punishment referencedOn Edits.punishment
    var staff by Edits.staff
    var timestamp by Edits.timestamp
    var previousReason by Edits.previousReason
    var newReason by Edits.newReason
    var previousEnd by Edits.previousEnd
    var newEnd by Edits.newEnd
    var previousAffectedPlayers by Edits.previousAffectedPlayers
    var newAffectedPlayers by Edits.newAffectedPlayers
    var removed by Edits.removed
    
    fun getType(): EditType {
        if (removed) return EditType.REMOVED
        if (previousReason != null) return EditType.REASON
        if (previousEnd != null && previousEnd!! != newEnd) return EditType.DURATION
        return EditType.AFFECTED_PLAYERS
    }
}

object Edits : IntIdTable() {
    val punishment = reference("punishment", Punishments, onDelete = ReferenceOption.CASCADE)
    val staff = varchar("staff", 36)
    val timestamp = datetime("timestamp").default(LocalDateTime.now())
    val previousReason = text("previous_reason").nullable()
    val newReason = text("new_reason").nullable()
    val previousEnd = datetime("previous_end").nullable()
    val newEnd = datetime("new_end").nullable()
    val previousAffectedPlayers = text("previous_affected_players").nullable()
    val newAffectedPlayers = text("new_affected_players").nullable()
    val removed = bool("removed").default(false)
    
    init {
        index(false, punishment)
        index(false, staff)
    }
}

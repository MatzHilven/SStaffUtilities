package com.matzhilven.staffutilities.punishment

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime

class Punishment(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Punishment>(Punishments)
    
    var punished by Punishments.punished
    var punisher by Punishments.punisher
    var offence by Punishments.offence
    var severityLevel by Punishments.severityLevel
    var reason by Punishments.reason
    var start by Punishments.start
    var end by Punishments.end
    var permanent by Punishments.permanent
    var removed by Punishments.removed
}

class AffectedUser(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AffectedUser>(AffectedUsers)
    
    var punishment by Punishment referencedOn AffectedUsers.punishment
    var uuid by AffectedUsers.uuid
}

object Punishments : IntIdTable() {
    val punished = varchar("punished", 36)
    val punisher = varchar("punisher", 36)
    val offence = text("offence")
    val severityLevel = integer("severity_level")
    val reason = text("reason")
    val start = datetime("start")
    val end = datetime("end")
    val permanent = bool("permanent")
    val removed = bool("removed")
    
    init {
        index(false, punished)
        index(false, punisher)
    }
}

object AffectedUsers : IntIdTable("affected_users") {
    val punishment = reference("punishment", Punishments, onDelete = ReferenceOption.CASCADE)
    val uuid = varchar("uuid", 36)
    
    init {
        index(true, punishment, uuid)
        index(false, punishment)
    }
}

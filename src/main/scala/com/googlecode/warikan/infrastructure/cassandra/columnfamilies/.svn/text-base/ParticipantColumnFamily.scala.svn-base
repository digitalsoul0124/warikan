package com.googlecode.warikan.infrastructure.cassandra.columnfamilies

import java.util.ArrayList

import scala.collection.JavaConversions._

import org.apache.cassandra.thrift._

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.infrastructure.cassandra._
import com.googlecode.warikan.infrastructure.uuid._
import CassandraMutation._
import CassandraRange._

/**
 * Participant ColumnFamily.
 * 
 * @author yukei
 */
object ParticipantColumnFamily {
    
    /** Name of Participant ColumnFamily. */
    val name:String = "Participant"

    /**
     * ParticipantColumnFamily as ColumnParent.
     * 
     * @return ColumnParent
     */
    def asColumnParent:ColumnParent = new ColumnParent("Participant")
    
    /**
     * Convert SuperColumn to Participant.
     * 
     * @param participantCoS SuperColumn of Participant
     * @return Participant
     */
    def convertToParticipant(participantCoS:ColumnOrSuperColumn):Participant = {
        val columns = participantCoS.super_column.columns
        var userName:String = null
        var roleName:String = null
        columns.foreach { column =>
            new String(column.name, "utf-8") match {
                case "userName" => userName = new String(column.value, "utf-8")
                case "roleName" => roleName = new String(column.value, "utf-8")
            }
        }
        new Participant(UserName(userName), Role(roleName))
    }

    /**
     * Create Participant Mutation from Party entity.
     * 
     * @param party Party entity
     * @param timestamp Timestamp
     * @return list of Mutation represents Participant rows
     */
    def createParticipantRows(party:Party, timestamp:Long):java.util.List[Mutation] = {
        val participantRows = new ArrayList[Mutation]
        party.participants.foreach { it =>
            
            val participant = it._2
                        
            val participantId:String = UUID.generateId
            
            val participantColumns = new ArrayList[Column]
            val userNameColumn = new Column("userName".getBytes("utf-8"), participant.userName.name.getBytes("utf-8"), timestamp)
            participantColumns.add(userNameColumn)
            val roleNameColumn = new Column("roleName".getBytes("utf-8"), participant.role.name.getBytes("utf-8"), timestamp)
            participantColumns.add(roleNameColumn)
            
            val participantMutation = createSuperColumnMutation(participantId.getBytes("utf-8"), participantColumns)
            participantRows.add(participantMutation)
            
        }
        participantRows
    }

}
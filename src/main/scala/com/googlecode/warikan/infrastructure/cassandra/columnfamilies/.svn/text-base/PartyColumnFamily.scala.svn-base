package com.googlecode.warikan.infrastructure.cassandra.columnfamilies

import java.util.Date
import java.util.ArrayList

import scala.collection.JavaConversions._

import org.apache.cassandra.thrift._

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.infrastructure.cassandra._
import CassandraMutation._
import CassandraRange._

/**
 * Party Column Family.
 * 
 * @author yukei
 */
object PartyColumnFamily {

    /** Name of Party ColumnFamily. */
    val name:String = "Party"

    /** Name of partyName Column. */
    val partyName:String = "partyName" 

    /** Name of schedule Column. */
    val schedule:String = "schedule"

    /** Name of location Column. */
    val location:String = "location"

    /** Name of sum Column */
    val sum:String = "sum"

    /**
     * Create ColumnParent for Party
     * 
     * @return Party as ColumnParent
     */
    def asColumnParent:ColumnParent = new ColumnParent("Party")

    /**
     * Convert List of ColumnOrSuperColumn to Party entiry.
     * 
     * @param id ID of Party entity
     * @param partyCoSs List of ColumnOrSuperColumn
     * @return Party entity
     */
    def convertToParty(id:String, partyCoSs:java.util.List[ColumnOrSuperColumn]):Party = {
        val party:Party = new Party(id)
        var partyName:String = null
        var schedule:Date = null
        var location:String = null
        partyCoSs.foreach { cos =>
            new String(cos.column.name, "utf-8") match {
                case "partyName" => partyName  = new String(cos.column.value, "utf-8")
                case "schedule" => schedule  = new Date(new String(cos.column.value, "utf-8").toLong)
                case "location" => location = new String(cos.column.value, "utf-8")
                case "sum" => party.sum = new String(cos.column.value, "utf-8").toInt
            }
        }
        party.info = PartyInformation(partyName, schedule, location)
        party
    }
    
    /**
     * Convert KeySlice to Party entity.
     * 
     * @param id ID of Party entity
     * @param partyKeySlice KeySlice for Party
     * @return Party entity
     */
    def convertToParty(id:String, partyKeySlice:KeySlice):Party = {
        convertToParty(id, partyKeySlice.columns)
    }

    /**
     * Create list of Mutation from Party.
     * 
     * @param party Party entity
     * @param timestamp Timestamp
     * @return List of Mutation representing Party entity
     */
    def createPartyColumns(party:Party, timestamp:Long):java.util.List[Mutation] = {

        val partyColumns = new ArrayList[Mutation]
        
        val partyNameMutation = createColumnMutation(PartyColumnFamily.partyName, party.info.name, timestamp)
        partyColumns.add(partyNameMutation)
        
        val scheduleMutation = createColumnMutation(PartyColumnFamily.schedule, java.lang.Long.toString(party.info.schedule.getTime), timestamp)
        partyColumns.add(scheduleMutation)

        val locationMutation = createColumnMutation(PartyColumnFamily.location, party.info.location, timestamp)
        partyColumns.add(locationMutation)
        
        val sumMutation = createColumnMutation(PartyColumnFamily.sum, party.sum.toString, timestamp)
        partyColumns.add(sumMutation)
        
        partyColumns
    }

}
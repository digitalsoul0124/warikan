package com.googlecode.warikan.infrastructure.repositories

import java.util.ArrayList
import java.util.Date
import java.util.HashMap

import scala.collection.JavaConversions._

import org.apache.log4j.Logger
import org.apache.cassandra.thrift._
import ConsistencyLevel._

import org.apache.thrift.protocol._
import org.apache.thrift.transport._

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.infrastructure.cassandra._
import com.googlecode.warikan.infrastructure.cassandra.columnfamilies._
import com.googlecode.warikan.infrastructure.uuid._
import CassandraMutation._
import CassandraRange._

/**
 * Implementation of Party Repository.
 * 
 * @author yukei
 */
class PartyRepositoryImpl {

    private val logger:Logger = Logger.getLogger(classOf[PartyRepositoryImpl])

    /** @inheritDoc */
    def nextId:String = UUID.generateId

    /** @inheritDoc */
    def add(party:Party) = {
        
        logger.info("party added. party:" + party)
        
        val timestamp = System.currentTimeMillis
        
        // inner map
        val innerMap = createInnerMap
        
        // party
        val partyColumns = PartyColumnFamily.createPartyColumns(party, timestamp)
        innerMap.put(PartyColumnFamily.name, partyColumns)
        
        // participants
        val participantRows = ParticipantColumnFamily.createParticipantRows(party, timestamp)
        innerMap.put(ParticipantColumnFamily.name, participantRows)
        
        // allots
        val allotRows = AllotColumnFamily.createAllotRows(party, timestamp)
        innerMap.put(AllotColumnFamily.name, allotRows)
        
        // mutation map
        val mutationMap = createMutationMap
        mutationMap.put(party.id, innerMap)
        
        val client:CassandraClient = CassandraClientFactory.openClient
        
        try {
            client.executeBatchMutate(mutationMap)
        } finally {
            client.close
        }
    }

    /** @inheritDoc */
    def forPartyId(id:String):Option[Party] = {
        
        val client:CassandraClient = CassandraClientFactory.openClient
        
        try {
            
            //party
            val partyCoSs = client.getSliceRangeAll(id, PartyColumnFamily.asColumnParent)
            logger.info("partyCoSs:" + partyCoSs)
            
            if (partyCoSs.isEmpty) return None
            
            val party:Party = PartyColumnFamily.convertToParty(id, partyCoSs)
            
            // participants
            val participantCoSs = client.getSliceRangeAll(id, ParticipantColumnFamily.asColumnParent)
            logger.info("participantCoSs:" + participantCoSs)

            participantCoSs.foreach { participantCoS =>
                val participant = ParticipantColumnFamily.convertToParticipant(participantCoS)
                party.putParticipant(participant.userName -> participant)
            }
                        
            // allots
            val allotCoSs = client.getSliceRangeAll(id, AllotColumnFamily.asColumnParent)
            logger.info("allotCoSs:" + allotCoSs)
                        
            allotCoSs.foreach { allotCoS =>
                val allot = AllotColumnFamily.convertToAllot(allotCoS)
                party.putAllot(allot)
            }
            
            return Option(party)
            
        } finally {
            client.close
        }
    }

    /** @inheritDoc */
    def forAll:List[Party] = {
        
        val client:CassandraClient = CassandraClientFactory.openClient
        
        try {
            
            // party
            val partyKeySlices = client.getRangeSlicesRangeAll(PartyColumnFamily.asColumnParent)
        
            if (partyKeySlices isEmpty) return List[Party]()
            
            // party keys
            val partyKeys = new ArrayList[String]()
            partyKeySlices.foreach { partyKeySlice =>
                partyKeys.add(partyKeySlice.key)
            }
            
            // participant
            val participantMap = client.multigetSliceRangeAll(partyKeys, ParticipantColumnFamily.asColumnParent)
            
            // allot
            val allotMap = client.multigetSliceRangeAll(partyKeys, AllotColumnFamily.asColumnParent)
            
            // convert
            var partyList = List[Party]()
            partyKeySlices.foreach { partyKeySlice =>

                logger.debug("partyKeySlice:" + partyKeySlice)
                
                // party
                val party = PartyColumnFamily.convertToParty(partyKeySlice.key, partyKeySlice)
                
                // participant
                participantMap(party.id).foreach { participantCoS =>
                    val participant = ParticipantColumnFamily.convertToParticipant(participantCoS)
                    party.putParticipant(participant.userName -> participant)
                }
                
                // allot
                allotMap(party.id).foreach { allotCoS =>
                    val allot = AllotColumnFamily.convertToAllot(allotCoS)
                    party.putAllot(allot)
                }
                partyList ::= party
            }
            return partyList
            
        } finally {
            client.close
        }
    }

    /** @inheritDoc */
    def paymentOf(id:String, name:UserName):Int = {
        assert(id != null)
        assert(name != null)
        
        val client:CassandraClient = CassandraClientFactory.openClient        
        
        try {
            val allotCoSs = client.getSliceRangeAll(id, AllotColumnFamily.asColumnParent)
            
            // search for participant's allot
            for (allotCoS <- allotCoSs) {
                val allot = AllotColumnFamily.convertToAllot(allotCoS)
                if (allot.userName == name) return allot.amount 
            }
            return 0
            
        } finally {
            client.close
        }
    }
    
    def update(party:Party) = {
        // delete-insert
        delete(party.id)
        add(party)
    }

    /** @inheritDoc */
    def updatePartyInfo(party:Party) = {
        
        val timestamp = System.currentTimeMillis
        
        // create inner map
        val innerMap = createInnerMap
        
        val partyColumns = PartyColumnFamily.createPartyColumns(party, timestamp)
        innerMap.put(PartyColumnFamily.name, partyColumns)
        
        // create outer map
        val mutationMap = createMutationMap(party.id, innerMap)
        
        val client:CassandraClient = CassandraClientFactory.openClient
        
        try {
            client.executeBatchMutate(mutationMap)
        } finally {
            client.close
        }
    }

    /** @inheritDoc */
    def updateAllots(party:Party) = {
        
        // TODO change model to contain mappingId
        deleteAllots(party.id)
        
        val timestamp = System.currentTimeMillis
        
        // create inner map
        val innerMap = createInnerMap
        
        val allotRows = AllotColumnFamily.createAllotRows(party, timestamp)
        innerMap.put(AllotColumnFamily.name, allotRows)
        
        // create outer map
        val mutationMap = createMutationMap(party.id, innerMap)
        
        // execute update
        val client:CassandraClient = CassandraClientFactory.openClient
        
        try {
            client.executeBatchMutate(mutationMap)
        } finally {
            client.close
        }
    }

    /** @inheritDoc */
    def delete(partyId:String) = {
        
        val timestamp = System.currentTimeMillis

        // create inner map
        val innerMap = createInnerMap
        innerMap.put(PartyColumnFamily.name, rowDeleteMutations(timestamp))
        innerMap.put(ParticipantColumnFamily.name, rowDeleteMutations(timestamp))
        innerMap.put(AllotColumnFamily.name, rowDeleteMutations(timestamp))
        
        // create outer map
        val mutationMap = createMutationMap(partyId, innerMap)
        
        // execute update
        val client:CassandraClient = CassandraClientFactory.openClient
        
        try {
            client.executeBatchMutate(mutationMap)
        } finally {
            client.close
        }
    }

    /** @inheritDoc */
    private def deleteAllots(partyId:String) = {
        
        val timestamp = System.currentTimeMillis
        
        val client:CassandraClient = CassandraClientFactory.openClient
        
        try {
            client.removeRow(partyId, AllotColumnFamily.asColumnPath, timestamp)
        } finally {
            client.close
        }
    }
}
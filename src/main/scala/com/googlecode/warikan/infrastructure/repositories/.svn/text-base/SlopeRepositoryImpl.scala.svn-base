package com.googlecode.warikan.infrastructure.repositories

import java.util.ArrayList
import java.util.HashMap

import scala.collection.JavaConversions._

import org.apache.log4j.Logger
import org.apache.cassandra.thrift._
import ConsistencyLevel._

import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.protocol.TProtocol
import org.apache.thrift.transport.TSocket
import org.apache.thrift.transport.TTransport

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.infrastructure.cassandra._
import com.googlecode.warikan.infrastructure.cassandra.columnfamilies._
import com.googlecode.warikan.infrastructure.uuid._
import CassandraMutation._
import CassandraRange._

/**
 * Implementation of Slope Repository.
 * 
 * @author yukei
 */
class SlopeRepositoryImpl {

    private val logger:Logger = Logger.getLogger(classOf[PartyRepositoryImpl])

    /** @inheritDoc */
    def nextId:String = UUID.generateId

    /** @inheritDoc */
    def add(slope:Slope) = {
        
        logger.info("slope added. slopeId:" + slope.id)
                
        val timestamp = System.currentTimeMillis
        
        // inner map
        val innerMap = createInnerMap

        val slopeId = slope.id
        
        // slope
        val slopeRows = new ArrayList[Mutation]
        val slopeColumnMutation:Mutation = 
            createColumnMutation(SlopeColumnFamily.slopeName, slope.name, timestamp)
        slopeRows.add(slopeColumnMutation)
        
        innerMap.put(SlopeColumnFamily.name, slopeRows)
        
        // weight
        val weightRows = new ArrayList[Mutation]
        slope.weights.foreach { it =>
        
            // mapping id
            val mappingId = UUID.generateId
        
            // columns
            val roleName:Column = new Column(WeightColumnFamily.roleNameAsByte, it._1.name.getBytes("utf-8"), timestamp)
            val weight:Column = new Column(WeightColumnFamily.weightAsByte, it._2.weight.toString.getBytes("utf-8"), timestamp)
            
            val mapping:java.util.List[Column] = new ArrayList[Column]()
            mapping.add(roleName)
            mapping.add(weight)
                        
            val rolesSuperColumnMutation:Mutation = createSuperColumnMutation(mappingId.getBytes("utf-8"), mapping)
            
            weightRows.add(rolesSuperColumnMutation)
            
        }
        innerMap.put(WeightColumnFamily.name, weightRows)
        
        // mutation map
        val mutationMap = createMutationMap
        mutationMap.put(slopeId, innerMap)
        
        val client:CassandraClient = CassandraClientFactory.openClient
        
        try {
            client.executeBatchMutate(mutationMap)
        } finally {
            client.close
        }
    }

    /** @inheritDoc */
    def forSlopeId(id:String):Option[Slope] = {
        
        val client:CassandraClient = CassandraClientFactory.openClient
        
        try {
        
            // slope
            val slopeNameColumnPath:ColumnPath = new ColumnPath(SlopeColumnFamily.name)
            slopeNameColumnPath.setColumn(SlopeColumnFamily.slopeNameAsByte)
        
            var slopeNameColumn:ColumnOrSuperColumn = null
            try {
                slopeNameColumn = client.get(id, slopeNameColumnPath)
            } catch { 
                case ex:NotFoundException => return None
            }
            val slopeName:String = new String(slopeNameColumn.column.value, "utf-8")
            val slope = new Slope(id, slopeName)
            
            // weight
            val weightColumnParent:ColumnParent = new ColumnParent(WeightColumnFamily.name)
            val weightCoSs = client.getSliceRangeAll(id, weightColumnParent)
            logger.info("weightCoSs:" + weightCoSs)
            
            weightCoSs.foreach { weightCoS =>
                WeightColumnFamily.populateWeights(slope, weightCoS)
            }
        
            return Option(slope)
        
        } finally {
            client.close
        }
    }

    /** @inheritDoc */
    def forAll:List[Slope] = {
        
        val client:CassandraClient = CassandraClientFactory.openClient

        try {
        
            // slope
            val slopeColumnParent:ColumnParent = new ColumnParent(SlopeColumnFamily.name)
            val slopeKeySlices = client.getRangeSlicesRangeAll(slopeColumnParent)
            logger.info("slopeKeySlices:" + slopeKeySlices)
            if (slopeKeySlices isEmpty) return List[Slope]()
            
            // weights
            val weightColumnParent:ColumnParent = new ColumnParent(WeightColumnFamily.name)
            
            val keys = new ArrayList[String]()
            slopeKeySlices.foreach { slopeKeySlice =>
                keys.add(slopeKeySlice.key)
            }
            
            val weightCoSMap = client.multigetSliceRangeAll(keys, weightColumnParent)
            logger.info("weightCoSMap:" + weightCoSMap)
            
            // slope list
            var slopeList = List[Slope]()
            slopeKeySlices.foreach { slopeKeySlice =>
                if (slopeKeySlice.columns.size > 0) { // see http://wiki.apache.org/cassandra/DistributedDeletes
                    val slope:Slope = SlopeColumnFamily.convertToSlope(slopeKeySlice)
                    weightCoSMap(slope.id).foreach { weightCoS =>
                        WeightColumnFamily.populateWeights(slope, weightCoS)
                    }
                    slopeList ::= slope
                }
            }
            return slopeList
        
        } finally {
            client.close
        }
    }

    /** @inheritDoc */
    def weightOf(slopeId:String, role:Role):AllotWeight = {
        
        val client:CassandraClient = CassandraClientFactory.openClient

        try {
            
            // weight
            val weightColumnParent:ColumnParent = new ColumnParent(WeightColumnFamily.name)
            val weightCoSs = client.getSliceRangeAll(slopeId, weightColumnParent)
            logger.info("weightCoSs:" + weightCoSs)
            
            for (weightCoS <- weightCoSs) {
                val weightMap = WeightColumnFamily.convertToWeightMap(weightCoS.super_column.columns)
                if (weightMap._1 == role) return weightMap._2
            }
            
            return AllotWeight(0)
            
        } finally {
            client.close
        }
    }

    /** @inheritDoc */
    def update(slope:Slope) = {
        // execute delete insert
        delete(slope.id)
        add(slope)
    }

    /** @inheritDoc */
    def delete(slopeId:String) = {
        
        val timestamp = System.currentTimeMillis
        
        val innerMap = createInnerMap
        innerMap.put(SlopeColumnFamily.name, rowDeleteMutations(timestamp))
        innerMap.put(WeightColumnFamily.name, rowDeleteMutations(timestamp))
        
        val mutationMap = createMutationMap(slopeId, innerMap)
        
        val client:CassandraClient = CassandraClientFactory.openClient
        
        try {
            client.executeBatchMutate(mutationMap)
        } finally {
            client.close
        }
    }
}
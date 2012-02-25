package com.googlecode.warikan.infrastructure.cassandra

import java.util.ArrayList
import java.util.HashMap

import org.apache.cassandra.thrift._
import org.apache.thrift.protocol._
import org.apache.thrift.transport._
import ConsistencyLevel._

/**
 * Cassandra Mutation Util.
 * 
 * Util Class for Operation on Mutation.
 * 
 * @author yukei
 */
object CassandraMutation {
    
    def createColumnMutation(name:String, value:String, timestamp:Long):Mutation = {
        createColumnMutation(name.getBytes("utf-8"), value.getBytes("utf-8"), timestamp)
    }

    def createColumnMutation(name:Array[Byte], value:Array[Byte], timestamp:Long):Mutation = {
        val column = new Column(name, value, timestamp)
        val cos = new ColumnOrSuperColumn
        cos.setColumn(column)
        
        val mutation = new Mutation
        mutation.setColumn_or_supercolumn(cos)
        mutation
    }
    
    def createSuperColumnMutation(id:Array[Byte], columns:java.util.List[Column]):Mutation = {
        val superColumn = new SuperColumn(id, columns)
        val cos = new ColumnOrSuperColumn
        cos.setSuper_column(superColumn)
            
        val mutation = new Mutation
        mutation.setColumn_or_supercolumn(cos)
        mutation
    }
    
    def createDeleteMutation(timestamp:Long, predicate:SlicePredicate):Mutation = {
        val deletion:Deletion = new Deletion
        deletion.setTimestamp(timestamp)
        deletion.setPredicate(predicate)
        
        val deleteMutation:Mutation = new Mutation
        deleteMutation.setDeletion(deletion)
        deleteMutation
    }
    
    def createDeleteMutation(timestamp:Long):Mutation = {
        val deletion:Deletion = new Deletion
        deletion.setTimestamp(timestamp)
        
        val deleteMutation:Mutation = new Mutation
        deleteMutation.setDeletion(deletion)
        deleteMutation        
    }
    
    def createInnerMap:HashMap[String, java.util.List[Mutation]] = {
        new HashMap[String, java.util.List[Mutation]] // CFName, Column
    }
    
    def createMutationMap:java.util.Map[String, java.util.Map[String, java.util.List[Mutation]]] = {
        new HashMap[String, java.util.Map[String, java.util.List[Mutation]]]() // key, innerMap
    }
    
    def createMutationMap(key:String, innerMap:java.util.Map[String, 
        java.util.List[Mutation]]):java.util.Map[String, java.util.Map[String, java.util.List[Mutation]]] = {
        
        val mutationMap = createMutationMap
        mutationMap.put(key, innerMap)
        mutationMap
        
    }
        
    def rowDeleteMutations(timestamp:Long):java.util.List[Mutation] = {
        val mutation = createDeleteMutation(timestamp)
        val mutations = new ArrayList[Mutation]()
        mutations.add(mutation)
        mutations
    }
}
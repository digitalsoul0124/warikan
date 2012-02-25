package com.googlecode.warikan.infrastructure.cassandra

import org.apache.cassandra.thrift._
import ConsistencyLevel._

import org.apache.thrift.protocol._
import org.apache.thrift.transport._

import com.googlecode.warikan.infrastructure.cassandra._
import CassandraMutation._
import CassandraRange._

/**
 * Cassandra Client.
 *
 * @author yukei
 */
class CassandraClient {

    private val keyspace:String = "warikan"

    private val transport:TTransport = new TSocket("localhost", 9160)
    private val protocol:TProtocol = new TBinaryProtocol(transport)
    private val client:Cassandra.Client = new Cassandra.Client(protocol)
    transport.open

    /**
     * Close transport.
     */
    def close = transport.close

    /**
     * Get the ColumnOrSuperColumn at the given ColumnPath.
     * 
     * @param key Key
     * @param columnPath ColumnPath
     * @return ColumnOrSuperColumn
     */
    def get(key:String, columnPath:ColumnPath):ColumnOrSuperColumn = {
        client.get(keyspace, key, columnPath, ONE)
    }
    
    /**
     * Get the group of columns contained by ColumnParent with all slice range.
     * 
     * @param key Key
     * @param columnParent ColumnParent
     * @return list of ColumnOrSuperColumn
     */
    def getSliceRangeAll(key:String, columnParent:ColumnParent):java.util.List[ColumnOrSuperColumn] = {
        client.get_slice(keyspace, key, columnParent, sliceRangeAll, ONE)
    }

    /**
     * Returns a list of slices for the all keys.
     * 
     * @param columnParent ColumnParent
     * @return list of KeySlice
     */
    def getRangeSlicesRangeAll(columnParent:ColumnParent):java.util.List[KeySlice] = {
        client.get_range_slices(keyspace, columnParent, sliceRangeAll, keyRangeAll, ONE)
    }

    /**
     * Retrieves slices for column_parent and predicate on each of the given keys in parallel.
     * 
     * @param keys list of key
     * @param columnParent ColumnParent
     * @return list of ColumnOrSuperColumn for each key
     */
    def multigetSliceRangeAll(keys:java.util.List[String], columnParent:ColumnParent):java.util.Map[String, java.util.List[ColumnOrSuperColumn]] = {
        client.multiget_slice(keyspace, keys, columnParent, sliceRangeAll, ONE)
    }

    /**
     * Remove data from the row specified by key at the granularity specified by column_path.
     * 
     * @param key Key
     * @param columnPath ColumnPath
     * @param timestampe TimeStamp
     */
    def removeRow(key:String, columnPath:ColumnPath, timestamp:Long) = {
        client.remove(keyspace, key, columnPath, timestamp, ONE)
    }

    /**
     * Executes the specified mutations.
     * 
     * @param mutationMap MutationMap
     */
    def executeBatchMutate(mutationMap:java.util.Map[String, java.util.Map[String, java.util.List[Mutation]]]) = {
        client.batch_mutate(keyspace, mutationMap, ONE)
    }
}
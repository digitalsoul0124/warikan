package com.googlecode.warikan.infrastructure.cassandra

import org.apache.cassandra.thrift._

/**
 * Cassandra Range Util.
 * 
 * Util class for operation on Slice/Key range.
 * 
 * @author yukei
 */
object CassandraRange {
    
    def createPredicateBy(columnNames:java.util.List[Array[Byte]]) = {
        val predicate = new SlicePredicate
        predicate.setColumn_names(columnNames)
        predicate
    }
    
    def sliceRangeAll:SlicePredicate = {
        val sliceRange = new SliceRange
        sliceRange.setStart(Array[Byte]())
        sliceRange.setFinish(Array[Byte]())
        
        val slicePredicate:SlicePredicate = new SlicePredicate
        slicePredicate.setSlice_range(sliceRange)
        
        slicePredicate
    }
    
    def keyRangeAll:KeyRange = {
        val partyKeyRange:KeyRange = new KeyRange
        partyKeyRange.setStart_key("")
        partyKeyRange.setEnd_key("")
        partyKeyRange
    }

}
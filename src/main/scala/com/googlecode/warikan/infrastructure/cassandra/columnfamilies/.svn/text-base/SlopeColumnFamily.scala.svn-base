package com.googlecode.warikan.infrastructure.cassandra.columnfamilies

import org.apache.cassandra.thrift._

import scala.collection.JavaConversions._

import com.googlecode.warikan.domain.models._

/**
 * Slope ColumnFamily.
 * 
 * @author yukei
 */
object SlopeColumnFamily {

    /** Name of Slope ColumnFamily. */
    val name:String = "Slope"

    /** Name of slopeName Column */
    val slopeName:String = "slopeName"

    /**
     * SlopeName in ByteArray form
     * 
     * @return slopeName as ByteArray
     */
    def slopeNameAsByte:Array[Byte] = slopeName.getBytes("utf-8")

    /**
     * Convert KeySlice to Slope entity.
     * 
     * @param keySlice KeySlice representing Slope
     */
    def convertToSlope(keySlice:KeySlice):Slope = {
        val slopeId = keySlice.key
        var currentSlopeName:String = null
        keySlice.columns.foreach { cos =>
            new String(cos.column.name, "utf-8") match {
                case slopeName => currentSlopeName = new String(cos.column.value, "utf-8") 
            }
        }
        new Slope(slopeId, currentSlopeName)
    }

}
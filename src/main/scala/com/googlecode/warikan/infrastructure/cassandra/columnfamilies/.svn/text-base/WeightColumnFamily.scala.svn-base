package com.googlecode.warikan.infrastructure.cassandra.columnfamilies

import java.util.ArrayList

import org.apache.cassandra.thrift._

import scala.collection.JavaConversions._

import com.googlecode.warikan.domain.models._

/**
 * Weight ColumnFamily.
 * 
 * @author yukei
 */
object WeightColumnFamily {

    /** Name of Weight ColumnFamily */
    def name:String = "Weight"

    /** roleName in ByteArray form */
    def roleNameAsByte:Array[Byte] = "roleName".getBytes("utf-8")

    /** weight in ByteArray form */
    def weightAsByte:Array[Byte] = "weight".getBytes("utf-8")

    /**
     * Populate weights in Slope entity.
     * 
     * @param slope Slope entity to populate
     * @param weightCoS SuperColumn representing weight
     */
    def populateWeights(slope:Slope, weightCoS:ColumnOrSuperColumn) = {
        val columns = weightCoS.super_column.columns
        slope.put(convertToWeightMap(columns))
    }

    /**
     * Convert list of Column to Weight Map
     * 
     * @param columns list of Column
     * @return Tuple(roleName -> weight)
     */
    def convertToWeightMap(columns:java.util.List[Column]):(Role, AllotWeight) = {
        var roleName:String = null
        var weight:Int = 0
        columns.foreach { column =>
            new String(column.name, "utf-8") match {
                case "roleName" => roleName = new String(column.value, "utf-8")
                case "weight" => weight = new String(column.value, "utf-8").toInt
            }
        }
        Role(roleName) -> AllotWeight(weight)
    }

}
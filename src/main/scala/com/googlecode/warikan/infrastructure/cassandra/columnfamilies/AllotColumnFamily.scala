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
 * Allot ColumnFamily.
 * 
 * @author yukei
 */
object AllotColumnFamily {
    
    /** Name of Allot ColumnFamily */
    val name:String = "Allot"

    /**
     * Allot ColumnFamily as ColumnParent.
     * 
     * @return ColumnParent
     */
    def asColumnParent:ColumnParent = new ColumnParent(name)
    
    /**
     * Allot ColumnFamily As ColumnPath
     * 
     * @return ColumnPath
     */
    def asColumnPath:ColumnPath = new ColumnPath(name)
    
    /**
     * Convert SuperColumn to Allot.
     * 
     * @param allotCoS SuperColumn of Allot
     * @return Allot
     */
    def convertToAllot(allotCoS:ColumnOrSuperColumn):Allot = {
        val columns = allotCoS.super_column.columns
        var userName:String = null
        var amount:Int = 0
        columns.foreach { column =>
            new String(column.name, "utf-8") match {
                case "userName" => userName = new String(column.value, "utf-8")
                case "amount" => amount = new String(column.value, "utf-8").toInt
            }
        }
        new Allot(UserName(userName), amount)
    }
    
    /**
     * Create Mutation representing Allot. 
     * 
     * @param party Party entity
     * @param timestamp Timestamp
     * @return list of Mutation
     */
    def createAllotRows(party:Party, timestamp:Long):java.util.List[Mutation] = {
        val allotRows = new ArrayList[Mutation]
        party.allots.foreach { it =>
            
            val allot = it._2
            
            val allotId:String = UUID.generateId
            
            val allotColumns = new ArrayList[Column]
            val userNameColumn = new Column("userName".getBytes("utf-8"), allot.userName.name.getBytes("utf-8"), timestamp)
            allotColumns.add(userNameColumn)
            val amountColumn = new Column("amount".getBytes("utf-8"), allot.amount.toString.getBytes("utf-8"), timestamp)
            allotColumns.add(amountColumn)
            
            val allotMutation = createSuperColumnMutation(allotId.getBytes("utf-8"), allotColumns)
            allotRows.add(allotMutation)
            
        }
        allotRows
    }

}
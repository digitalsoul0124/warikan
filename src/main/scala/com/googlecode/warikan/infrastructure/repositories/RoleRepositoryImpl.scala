package com.googlecode.warikan.infrastructure.repositories

import scala.collection.JavaConversions._

import org.apache.cassandra.thrift._

import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.protocol.TProtocol
import org.apache.thrift.transport.TSocket
import org.apache.thrift.transport.TTransport

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.infrastructure.cassandra._
import CassandraRange._

/**
 * Implementation of Role repository.
 * 
 * @author yukei
 */
class RoleRepositoryImpl {

    /** @inheritDoc */
    def forAll:List[Role] = {
        val keySlices = getRoleColumns
        createRoleList(keySlices)
    }

    private def getRoleColumns:java.util.List[KeySlice] = {
        val client:CassandraClient = CassandraClientFactory.openClient
        try {
            val columnParent:ColumnParent = new ColumnParent("Role")
            val keySlices = client.getRangeSlicesRangeAll(columnParent)
            return keySlices
        } finally { 
            client.close
        }
    }

    private def createRoleList(keySlices:java.util.List[KeySlice]):List[Role] = {
        var roles = List[Role]()
        keySlices.foreach { keySlice =>
            val role = convertToRole(keySlice)
            roles ::= role
        }
        roles
    }

    private def convertToRole(keySlice:KeySlice):Role = {
        val roleCoSs = keySlice.columns
        var roleName:String = null
        roleCoSs.foreach { roleCoS =>
            val column = roleCoS.column
            new String(column.name, "utf-8") match {
                case "roleName" => roleName = new String(column.value, "utf-8")
            }
        }
        Role(roleName)
    }

}
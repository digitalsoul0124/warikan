package com.googlecode.warikan.infrastructure.cassandra

/**
 * CassandraClient Factory.
 * 
 * Factory class for Cassandra Client.
 * 
 * @author yukei
 */
object CassandraClientFactory {
    
    def openClient:CassandraClient = new CassandraClient

}
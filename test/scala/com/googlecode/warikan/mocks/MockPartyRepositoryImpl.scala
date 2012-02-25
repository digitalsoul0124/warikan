package com.googlecode.warikan.mocks

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.infrastructure.repositories._

class MockPartyRepositoryImpl extends PartyRepositoryImpl {

    var parties = Map[String, Party]()
    
    override def nextId:String = (parties.size + 1).toString
    
    override def add(party:Party) = parties += (party.id -> party)
    
    override def forPartyId(id:String):Option[Party] = 
        if (parties.contains(id)) Option[Party](parties(id)) else None
    
    override def forAll:List[Party] = parties.values.toList
    
    override def paymentOf(id:String, name:UserName):Int = { 
        if(parties contains id) parties(id).allots(name).amount else 0
    }
    
    override def updatePartyInfo(party:Party) = {
        add(party)
    }
    
    override def updateAllots(party:Party) = {
        add(party)
    }
    
    def initialize = parties = Map[String, Party]()
    
}
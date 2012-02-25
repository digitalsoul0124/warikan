package com.googlecode.warikan.domain.contexts

import org.junit._
import Assert._

import com.googlecode.warikan.domain.contexts._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

import com.googlecode.warikan.mocks._

class AccountingTest extends InjectorInitializer {
    
    @Before
    def setup = {
        PartyRepository.repository.asInstanceOf[MockPartyRepositoryImpl].initialize
        SlopeRepository.repository.asInstanceOf[MockSlopeRepositoryImpl].initialize
    }
    
    @Test
    def shouldExecuteAccounting = {
        
        // id
        val slopeId:String = 1.toString
        val partyId:String = 2.toString
        
        // Preparation
        val slope:Slope = new Slope(slopeId)
        slope.put(Role("Chief") -> AllotWeight(10))
        slope.put(Role("Novice") -> AllotWeight(5))
        SlopeRepository.add(slope)
        
        // Party
        val party:Party = new Party(partyId)
        
        // Planning
        party.putParticipant(UserName("Jack") -> new Participant(UserName("Jack"), Role("Chief")))
        party.putParticipant(UserName("John") -> new Participant(UserName("John"), Role("Novice")))
        party.putParticipant(UserName("Paul") -> new Participant(UserName("Paul"), Role("Novice")))
        
        // Paying
        party.sum = 10000
        
        PartyRepository.add(party)
        
        /* Accounting */
        val accounting:Accounting = new Accounting(partyId)
        accounting.adjustBy(slopeId)
        accounting.commit
        
        assertEquals(5000, PartyRepository.paymentOf(partyId, UserName("Jack")))
        assertEquals(2500, PartyRepository.paymentOf(partyId, UserName("John")))
        assertEquals(2500, PartyRepository.paymentOf(partyId, UserName("Paul")))
        
    }
}
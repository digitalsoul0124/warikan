package com.googlecode.warikan.domain.contexts

import java.util._

import org.junit._
import Assert._

import com.googlecode.warikan.domain.contexts._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

import com.googlecode.warikan.mocks._

class IntegrationTest extends InjectorInitializer {
    
    private val _2010_06_03:Date = new GregorianCalendar(2010, 6 - 1, 3).getTime
    
    @Before
    def setup = {
        PartyRepository.repository.asInstanceOf[MockPartyRepositoryImpl].initialize
        SlopeRepository.repository.asInstanceOf[MockSlopeRepositoryImpl].initialize
    }
    
    @Test
    def integratePlanningAndPaying = {
        
        val planning:Planning = new Planning
        val info = PartyInformation("Chris's Birthday", _2010_06_03, "Tokyo")
        planning.setup(info)
        
        planning.addPerticipant(UserName("A"), Role("a"))
        planning.addPerticipant(UserName("B"), Role("b"))
        planning.commit
        
        val partyId:String = planning.party.id
        
        val paying:Paying = new Paying(partyId)
        paying.pay(10000)
        paying.commit
        
        val party:Party = PartyRepository.forPartyId(partyId).get
        
        assertEquals(2, party.participants.size)
        assertEquals(10000, party.sum)
        
    }
    
    @Test
    def integrateAll = {
        
        // Preparation
        val preparation:Preparation = new Preparation("slope 1")
        preparation.addMapping(Role("Chief"), AllotWeight(4))
        preparation.addMapping(Role("Manager"), AllotWeight(3))
        preparation.addMapping(Role("Member"), AllotWeight(2))
        preparation.addMapping(Role("Novice"), AllotWeight(1))
        preparation.commit
        
        // id
        val slopeId = preparation.slope.id

        // Planning
        val planning:Planning = new Planning
        val info = PartyInformation("Chris's Birthday", _2010_06_03, "Tokyo")
        planning.setup(info)
        planning.addPerticipant(UserName("Ken"), Role("Chief"))
        planning.addPerticipant(UserName("Taro"), Role("Manager"))
        planning.addPerticipant(UserName("Hanako"), Role("Member"))
        planning.addPerticipant(UserName("Yuji"), Role("Novice"))
        planning.commit
        
        val partyId:String = planning.party.id
        
        // Paying
        val paying:Paying = new Paying(partyId)
        paying.pay(10000)
        paying.commit
        
        // Accounting
        val accounting:Accounting = new Accounting(partyId)
        accounting.adjustBy(slopeId)
        accounting.commit
        
        // verify
        val accounting2:Accounting = new Accounting(partyId)
        val party:Party = accounting2.party
        assertEquals("Chris's Birthday", party.info.name)
        assertEquals(_2010_06_03, party.info.schedule)
        assertEquals("Tokyo", party.info.location)
        
        assertEquals(4000, PartyRepository.paymentOf(partyId, UserName("Ken")))
        assertEquals(3000, PartyRepository.paymentOf(partyId, UserName("Taro")))
        assertEquals(2000, PartyRepository.paymentOf(partyId, UserName("Hanako")))
        assertEquals(1000, PartyRepository.paymentOf(partyId, UserName("Yuji")))
        
    }

}
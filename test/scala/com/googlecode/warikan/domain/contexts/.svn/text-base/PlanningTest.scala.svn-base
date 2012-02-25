package com.googlecode.warikan.domain.contexts

import java.text._
import java.util._

import org.junit._
import Assert._
import com.googlecode.warikan.domain.contexts._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

import com.googlecode.warikan.mocks._

class PlanningTest extends InjectorInitializer {
    
    private val _2010_06_03:Date = new GregorianCalendar(2010, 6 - 1, 3).getTime
    
    @Before
    def setup = {
        PartyRepository.repository.asInstanceOf[MockPartyRepositoryImpl].initialize
    }
    
    @Test
    def shouldSetupParty = {
        
        val planning:Planning = new Planning()
        val info = PartyInformation("Chris's Birthday", _2010_06_03, "Tokyo")
        planning.setup(info)
                
        assertEquals("Chris's Birthday", planning.party.info.name)
        assertEquals(_2010_06_03, planning.party.info.schedule)
        assertEquals("Tokyo", planning.party.info.location)
        
    }

    @Test
    def shouldGatherUpPerticipants = {
        
        val context:Planning = new Planning
        val info = PartyInformation("Chris's Birthday", _2010_06_03, "Tokyo")
        context.setup(info)

        context.addPerticipant(UserName("Jack"), Role("Chief"))
        context.addPerticipant(UserName("Paul"), Role("Manager"))
        context.addPerticipant(UserName("John"), Role("Member"))
        context.addPerticipant(UserName("Mike"), Role("Novice"))
        
        assertEquals(4, context.party.participantsCount)
    }
    
    @Test
    def shouldCommitTransaction = {
        
        val context:Planning = new Planning
        val info = PartyInformation("Chris's Birthday", _2010_06_03, "Tokyo")
        context.setup(info)
        
        context.addPerticipant(UserName("Jack"), Role("Chief"))
        context.addPerticipant(UserName("Paul"), Role("Manager"))
        context.addPerticipant(UserName("John"), Role("Member"))
        context.addPerticipant(UserName("Mike"), Role("Novice"))
        context.commit
        
        val id:String = context.party.id
        
        val context2:Planning = new Planning
        context2.populate(id)
        
        assertEquals(4, context2.party.participantsCount)
        
    }

}
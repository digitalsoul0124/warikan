package com.googlecode.warikan.domain.contexts

import org.junit._
import Assert._

import com.googlecode.warikan.domain.contexts._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

import com.googlecode.warikan.mocks._

class EditSlopeTest extends InjectorInitializer {
    
    @Before
    def setup = {
        PartyRepository.repository.asInstanceOf[MockPartyRepositoryImpl].initialize
        SlopeRepository.repository.asInstanceOf[MockSlopeRepositoryImpl].initialize
    }
    
    @Test
    def shouldExecuteDeleteSlope = {

        // id
        val slopeId:String = 1.toString
        
        // Preparation
        val slope:Slope = new Slope(slopeId)
        slope.put(Role("Chief") -> AllotWeight(10))
        slope.put(Role("Novice") -> AllotWeight(5))
        SlopeRepository.add(slope)
        
        // execute delete
        new EditSlope(slopeId).delete
        
        // verify
        assertTrue(SlopeRepository.forSlopeId(slopeId).isEmpty)
        
    }
}
package com.googlecode.warikan.domain.contexts

import org.junit._
import Assert._

import com.googlecode.warikan.domain.contexts._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

class PreparationTest extends InjectorInitializer {
    
    @Test
    def shouldCreateRoleWeightMapping = {
        
        val context:Preparation = new Preparation("slopeName")
        context.addMapping(Role("Chief"), AllotWeight(10))
        context.addMapping(Role("Leader"), AllotWeight(9))
        context.addMapping(Role("Member"), AllotWeight(8))
        context.addMapping(Role("Novice"), AllotWeight(7))
        context.commit
        
        val slopeId = context.slope.id
        
        assertEquals(AllotWeight(10), SlopeRepository.weightOf(slopeId, Role("Chief")))
        assertEquals(AllotWeight(9), SlopeRepository.weightOf(slopeId, Role("Leader")))
        assertEquals(AllotWeight(8), SlopeRepository.weightOf(slopeId, Role("Member")))
        assertEquals(AllotWeight(7), SlopeRepository.weightOf(slopeId, Role("Novice")))
        
    }
        
    @Test
    def shouldSetupSlope = {
        
        val preparation:Preparation = new Preparation("slope 1")
        preparation.commit
        
        assertEquals("slope 1", preparation.slope.name)
        
    }

}
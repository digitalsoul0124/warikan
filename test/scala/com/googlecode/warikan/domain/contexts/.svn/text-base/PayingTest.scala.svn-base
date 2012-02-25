package com.googlecode.warikan.domain.contexts

import org.junit._
import Assert._
import com.googlecode.warikan.domain.contexts._

class PayingTest extends InjectorInitializer {
    
    @Test
    def shouldPayForParty() = {
        
        val context:Paying = new Paying("10")
        context.pay(10000)
        
        assertEquals(10000, context.party.sum)
    }

    @Test
    def shoulCommitTransaction() = {

        val partyId:String = 20.toString
        
        val context:Paying = new Paying(partyId)
        context.pay(10000)
        context.commit
        
        val context2:Paying = new Paying(partyId)
        
        assertEquals(10000, context2.party.sum)
        
    }
}
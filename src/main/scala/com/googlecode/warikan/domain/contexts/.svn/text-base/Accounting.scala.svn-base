package com.googlecode.warikan.domain.contexts

import com.googlecode.warikan.domain.contexts.rootpopulaters._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._
import com.googlecode.warikan.domain.roles._
import com.googlecode.warikan.domain.roles.methodful._

/**
 * Accounting Context.
 * 
 * @author yukei
 */
class Accounting(private val id:String) extends PartyPopulater with SlopePopulater {
   
    private val accountant:Accountant = new Party(id) with PartyAccountant with PartyPieMaker

    private val pieMaker:PieMaker = accountant.asInstanceOf[PieMaker]

    /** Party entity. */
    val party:Party = populateParty(accountant)    

    /**
     * Execute accounting.
     * 
     * Calculate each participants's payment by defined slope.
     * 
     * @param slopeId ID of slope to be used
     */
    def adjustBy(slopeId:String) = accountant.adjust(pieMaker, slopeId)
    
    /**
     * Clear allots in Party entity.
     */
    def clearAllots = party.clearAllots
    
    /**
     * Set payment of defined Participant.
     * 
     * @param participantName name of Participant
     * @param payment payment of Participant
     */
    def setPaymentOfParticipant(participantName:UserName, payment:Int) = {
        val allot:Allot = new Allot(participantName, payment)
        party.putAllot(allot)
    }

    /**
     * Update Allots in Party entity.
     */
    def commit = PartyRepository.updateAllots(party)

}
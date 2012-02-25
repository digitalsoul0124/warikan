package com.googlecode.warikan.application.actions

import java.text._

import com.googlecode.warikan.presentation.pages.forms._
import com.googlecode.warikan.domain.contexts._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

/**
 * Paying Action.
 * 
 * Action class for Paying page.
 *
 * @author yukei
 */
@serializable
class PayingAction {
    
    private val format = new SimpleDateFormat("yyyy/MM/dd")
    
    /**
     * Execute paying.
     * 
     * @param partyId ID of Party entity
     * @param sum sum of Party
     */
    def pay(partyId:String, sum:Int) = {
        val paying:Paying = new Paying(partyId)
        paying.pay(sum)
        paying.commit
    }

    /**
     * Create MainForm for Paying page.
     * 
     * @param partyId ID for Party
     * @return MainForm for Paying page
     */
    def createMainForm(partyId:String):PayingMainForm = {
        val mainFormModel = new PayingMainForm
        val partyOption = PartyRepository.forPartyId(partyId)
        if (partyOption.isDefined) {
            populateMainFormModelWithParty(mainFormModel, partyOption.get)
        }
        mainFormModel
    }
    
    private def populateMainFormModelWithParty(mainFormModel:PayingMainForm, party:Party) = {
        mainFormModel.partyName  = party.info.name
        mainFormModel.schedule  = format.format(party.info.schedule)
        mainFormModel.location = party.info.location
        mainFormModel.sum = party.sum
    }
}
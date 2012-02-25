package com.googlecode.warikan.application.actions

import java.text._
import java.util.ArrayList

import com.googlecode.warikan.presentation.pages.items._
import com.googlecode.warikan.domain.contexts._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

/**
 * Top Action.
 * 
 * Action class for Top page.
 * 
 * @author yukei
 */
@serializable
class TopAction {

    private val format:SimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd")

    /**
     * Delete Party.
     * 
     * @param partyId ID for Party entity
     */
    def deleteParty(partyId:String) = new EditParty(partyId).delete

    /**
     * Create list of PartyListItem.
     * 
     * @return list of PartyListItem
     */
    def createPartyList:java.util.List[PartyListItem] = {
        val partyListItems = new ArrayList[PartyListItem]()
        for (party <- PartyRepository.forAll) {
            if(party.info != null) partyListItems.add(convertToPartyListItem(party))
        }
        partyListItems
    }

    private def convertToPartyListItem(party:Party):PartyListItem = {
        val partyListItem:PartyListItem = new PartyListItem
        partyListItem.partyId = party.id
        partyListItem.partyName = party.info.name
        partyListItem.schedule  = format.format(party.info.schedule)
        partyListItem.location = party.info.location
        partyListItem.sum = party.sum.toString
        partyListItem
    }

}
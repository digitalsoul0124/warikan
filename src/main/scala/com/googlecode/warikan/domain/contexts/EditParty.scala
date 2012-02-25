package com.googlecode.warikan.domain.contexts

import java.util.Date

import com.googlecode.warikan.domain.contexts.rootpopulaters._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._
import com.googlecode.warikan.domain.roles._
import com.googlecode.warikan.domain.roles.methodful._

/**
 * Edit Party Context.
 * 
 * @author yukei
 */
class EditParty(private val partyId:String) extends PartyPopulater {

    private val organizer:Organizer = new Party(partyId) with PartyOrganizer

    /** Party entity. */
    val party:Party = populateParty(organizer)

    /**
     * Update basic information of Party.
     * 
     * @param partyName Party name
     * @param schedule Schedule
     * @param location Location
     */
    def updatePartyInfo(info:PartyInformation) = {
        organizer.updateInfo(info)
    }

    /**
     * Update Participant in Party
     * 
     * @param participants
     */
    def updateParticipant(participants:Map[UserName, Role]) = {
        var participantSet:Set[Participant] = Set[Participant]()
        participants.foreach { it =>
            val participant:Participant = new Participant(it._1, it._2)
            participantSet += participant
        }
        organizer.updateParticipants(participantSet)
    }

    /** Clear Allot. */
    def clearAllot = organizer.clearAllot

    /** Update Party entity. */
    def commitEdit = PartyRepository.update(party)

    /** Delete Party entity. */
    def delete = PartyRepository.delete(partyId)

}
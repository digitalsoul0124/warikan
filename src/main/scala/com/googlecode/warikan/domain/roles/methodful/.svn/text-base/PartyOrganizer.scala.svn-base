package com.googlecode.warikan.domain.roles.methodful

import java.util.Date

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.roles._

/**
 * Party Organizer.
 * 
 * Methodful Role of Organizer
 * 
 * @author yukei
 */
trait PartyOrganizer extends Organizer {

    var info:PartyInformation

    def putParticipant(entry:(UserName, Participant))

    def setAllots(allots:Map[UserName, Allot])

    /**
     * Update information.
     * 
     * @param info information of the Party
     * @param schedule 
     * @param location
     */
    def updateInfo(info:PartyInformation) = {
        this.info = info
    }

    /**
     * Update participants 
     */
    def updateParticipants(participants:Set[Participant]) = {
        participants.foreach { participant =>
            putParticipant(participant.userName -> participant)
        }
    }

    /**
     * clear Allots in Party
     */
    def clearAllot = setAllots(Map[UserName, Allot]())

}
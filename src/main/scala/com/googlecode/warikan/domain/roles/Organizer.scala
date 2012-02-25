package com.googlecode.warikan.domain.roles

import java.util.Date

import com.googlecode.warikan.domain.models._

/**
 * Organizer.
 * 
 * @author yukei
 */
trait Organizer {

    /**
     * Update basic information of Party.
     * 
     * @param info information of the Party
     */
    def updateInfo(info:PartyInformation)

    /**
     * Update Participants.
     * 
     * @param participant set of participant
     */
    def updateParticipants(participants:Set[Participant])

    /**
     * Clear Allot.
     */
    def clearAllot

}
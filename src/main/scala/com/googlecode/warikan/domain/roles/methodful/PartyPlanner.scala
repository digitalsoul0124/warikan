package com.googlecode.warikan.domain.roles.methodful

import java.util.Date

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.roles._

/**
 * Party Planner.
 * 
 * @author yukei
 */
trait PartyPlanner extends Planner {

    val id:String

    var info:PartyInformation

    def putParticipant(entry:(UserName, Participant))

    /** @inheritDoc */
    def setup(info:PartyInformation) {
        this.info = info
    }

    /** @inheritDoc */
    def addParticipant(participant:Participant) = 
        putParticipant(participant.userName -> participant)

}
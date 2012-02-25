package com.googlecode.warikan.domain.models

import java.util.Date

/**
 * Party entity.
 * 
 * @author yukei
 */
@serializable
class Party(val id:String) {

    var info:PartyInformation = _
    
    /** Sum of Party */
    var sum:Int = 0

    private var _participants = Map[UserName, Participant]()

    private var _allots = Map[UserName, Allot]()

    /** 
     * Get Participants.
     * 
     * @return Participant map
     */
    def participants:Map[UserName, Participant] = _participants 

    /**
     * Put participant into participant map.
     * 
     * @param taple(name of Participant, Participant)
     */
    def putParticipant(entry:(UserName, Participant)) = {
        _participants += entry
    }

    /**
     * Participant Count.
     * 
     * @return Count of Participants
     */
    def participantsCount:Int = _participants.size

    /**
     * Get Allots.
     * 
     * @return Allot map
     */
    def allots:Map[UserName, Allot] = _allots 

    /**
     * Put Allot into Party.
     * 
     * @param allot Allot
     */
    def putAllot(allot:Allot) = {
        _allots += allot.userName -> allot
    }

    /**
     * Set Allots into Party.
     * 
     * This method replaces whole Allot map in Party.
     * 
     * @param allots Allot map
     */
    def setAllots(allots:Map[UserName, Allot]) = {
        this._allots  = allots
    }

    /**
     * Clear Allots.
     */
    def clearAllots = {
        this._allots = Map[UserName, Allot]()
    }

    /**
     * Get payment of specified participant.
     * 
     * @param name name of participant.
     * @return Payment of specified participant
     */
    def paymentOf(name:UserName):Int = {
        var payment:Int = 0
        if(allots.contains(name)) {
            payment = allots(name).amount
        }
        payment
    }

    /**
     * Populate the Party with another Party.
     * 
     * @param another another party
     */
    def populateWith(another:Party) = {
        this.info = another.info
        this.sum = another.sum 
        this._participants = another._participants
        this._allots = another._allots
    }

    /**
     * To String.
     * 
     * @return Expression of the Party
     */
    override def toString:String = {
        val sb:StringBuilder = new StringBuilder
        sb.append("id:").append(id).append(",")
        sb.append("info:").append(info).append(",")
        sb.append("sum:").append(sum).append(",")
        sb.append("participants:").append(_participants).append(",")
        sb.append("allots:").append(_allots)
        sb.toString
    }

}
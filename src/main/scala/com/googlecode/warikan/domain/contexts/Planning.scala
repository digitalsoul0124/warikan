package com.googlecode.warikan.domain.contexts

import java.util.Date

import com.googlecode.warikan.domain.contexts.rootpopulaters._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._
import com.googlecode.warikan.domain.roles._
import com.googlecode.warikan.domain.roles.methodful._

/**
 * Planning context.
 * 
 * @author yukei
 */
class Planning extends PartyPopulater {

    private var planner:Planner = null 

    /** Party entity */
    var party:Party = null

    /**
     * Set up Party with basic information.
     * 
     * @param info information of the Party
     */
    def setup(info:PartyInformation) = {
        
        val id:String = PartyRepository.nextId
        val planner:Planner = new Party(id) with PartyPlanner
        
        planner.setup(info)
        
        this.planner = planner
        this.party =  planner.asInstanceOf[Party]
        
    }

    /**
     * Populate with id.
     * 
     * @param id ID of Party entity.
     */
    def populate(id:String) = {
        
        val planner:Planner = new Party(id) with PartyPlanner 
        val party:Party = populateParty(planner)
        
        this.planner = planner
        this.party = party
        
    }

    /**
     * Add Participant.
     * 
     * @param name name of Participant
     * @param role role of Participant
     */
    def addPerticipant(name:UserName, role:Role) = 
        planner.addParticipant(new Participant(name, role))

    /**
     * Commit modification.
     */
    def commit = PartyRepository.add(party) 

}
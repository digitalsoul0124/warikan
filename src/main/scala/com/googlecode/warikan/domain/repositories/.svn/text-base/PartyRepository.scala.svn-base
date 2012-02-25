package com.googlecode.warikan.domain.repositories

import org.apache.log4j.Logger

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.infrastructure.inject._
import com.googlecode.warikan.infrastructure.repositories._

/**
 * Party Repository.
 * 
 * @author yukei
 */
object PartyRepository {

    /** Repository implementation. */
    val repository:PartyRepositoryImpl = Injector.getInstance(classOf[PartyRepositoryImpl])

    /** 
     * Get id for new Party.
     * 
     * @return id
     */
    def nextId:String = repository.nextId

    /**
     * Add new Party to Repository.
     * 
     * @param party Party to be added.
     */
    def add(party:Party) = repository.add(party)

    /**
     * Search for a Party by id.
     * 
     * @param id 
     * @return Party entity
     */
    def forPartyId(id:String):Option[Party] = repository.forPartyId(id)

    /**
     * Search for All Party in Repository.
     * 
     * @return list of Party entity
     */
    def forAll:List[Party] = repository.forAll

    /**
     * Get payment of specified participant in specified party.
     * 
     * @param partyId id of Party entity
     * @param name Name of Participant
     */
    def paymentOf(partyId:String, userName:UserName):Int = repository.paymentOf(partyId, userName)

    /**
     * Update the Party in repository with specified Party.
     * 
     * @param party 
     */
    def update(party:Party) = repository.update(party)

    /**
     * Update basic information of the Party.
     * 
     * @param party
     */
    def updatePartyInfo(party:Party) = repository.updatePartyInfo(party)

    /**
     * Update Allot with specified Party.
     * 
     * @param party
     */
    def updateAllots(party:Party) = repository.updateAllots(party)

    /**
     * Delete the party from repository.
     * 
     * @param partyId ID of Party to delete
     */
    def delete(partyId:String) = repository.delete(partyId)

}
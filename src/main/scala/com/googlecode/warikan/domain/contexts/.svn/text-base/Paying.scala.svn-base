package com.googlecode.warikan.domain.contexts

import com.googlecode.warikan.domain.contexts.rootpopulaters._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._
import com.googlecode.warikan.domain.roles._
import com.googlecode.warikan.domain.roles.methodful._

/**
 * Paying Context.
 * 
 * @author yukei
 */
class Paying(private val id:String) extends PartyPopulater {

    private val payer:Payer = new Party(id) with PartyPayer

    /** Party entity. */
    val party:Party = populateParty(payer)

    /**
     * Pay sum of the party.
     * 
     * @param sum sum of party
     */
    def pay(sum:Int) = payer.pay(sum)

    /**
     * Commit modification.
     */
    def commit = {
        // FIXME update sum column only
        PartyRepository.updatePartyInfo(party)
    }

}
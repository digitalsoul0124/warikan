package com.googlecode.warikan.domain.contexts.rootpopulaters

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

/**
 * Party Populater.
 * 
 * @author yukei
 */
trait PartyPopulater {

    /**
     * Populate Party in Role object.
     * 
     * @return Party entity
     */
    def populateParty(role:Object):Party = {
        val party:Party = role.asInstanceOf[Party]
        populate(party)
        party
    }

    private def populate(party:Party) = {
        val currentParty = PartyRepository.forPartyId(party.id)
        if (currentParty.isDefined) {
            party.populateWith(currentParty.get)
        }
        
    }

}
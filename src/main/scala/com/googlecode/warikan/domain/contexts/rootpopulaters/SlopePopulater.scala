package com.googlecode.warikan.domain.contexts.rootpopulaters

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

/**
 * Slope Populater.
 * 
 * @author yukei
 */
trait SlopePopulater {
    
    def populateSlope(role:Object):Slope = {
        val slope:Slope = role.asInstanceOf[Slope]
        populate(slope)
        slope
    }

    private def populate(slope:Slope) = {
        val currentSlope = SlopeRepository.forSlopeId(slope.id)
        if (currentSlope.isDefined) {
            slope.populateWith(currentSlope.get)
        }
    }

}
package com.googlecode.warikan.domain.contexts

import com.googlecode.warikan.domain.contexts.rootpopulaters._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._
import com.googlecode.warikan.domain.roles._
import com.googlecode.warikan.domain.roles.methodful._

/**
 * Edit Slope Context.
 * 
 * @author yukei
 */
class EditSlope(private val slopeId:String) extends SlopePopulater {

    private val administrator:Administrator = new Slope(slopeId) with SlopeAdministrator

    /** Slope entity */
    val slope:Slope = populateSlope(administrator)

    /**
     * Delete Slope entity.
     */
    def delete = {
        // TODO think about responsibility
        SlopeRepository.delete(slopeId)
    }

    /**
     * Update name of Slope.
     * 
     * @param slopeName Name of Slope
     */
    def updateName(slopeName:String) = administrator.updateName(slopeName)

    /**
     * Update Weights.
     * 
     * @param weights 
     */
    def updateWeights(weights:Map[Role, AllotWeight]) = administrator.updateWeights(weights)

    /**
     * Commit modification.
     */
    def commitEdit = SlopeRepository.update(slope)

}
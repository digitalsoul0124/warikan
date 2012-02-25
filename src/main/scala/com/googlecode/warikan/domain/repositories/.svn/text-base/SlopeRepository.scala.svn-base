package com.googlecode.warikan.domain.repositories

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.infrastructure.inject._
import com.googlecode.warikan.infrastructure.repositories._

/**
 * Slope Repository.
 * 
 * @author yukei
 */
object SlopeRepository {

    /** Implementation of Slope repository. */
    val repository:SlopeRepositoryImpl = Injector.getInstance(classOf[SlopeRepositoryImpl])

    /**
     * Get id for new Slope.
     * 
     * @return id for new Slope
     */
    def nextId:String = repository.nextId.toString

    /**
     * Add Slope.
     * 
     * @param slope created Slope
     */
    def add(slope:Slope) = repository.add(slope)

    /**
     * Search for Slope with specified id.
     * 
     * @param id id of Slope entity
     * @return Slope entity
     */
    def forSlopeId(id:String):Option[Slope] = repository.forSlopeId(id)

    /**
     * Get all Slope in repository.
     * 
     * @return list of Slope
     */
    def forAll:List[Slope] = repository.forAll

    /**
     * Get weight of specified role in specified Slope.
     * 
     * @param slopeId id of Slope entity
     * @param role name of role
     * @return Weight
     */
    def weightOf(slopeId:String, role:Role):AllotWeight = repository.weightOf(slopeId, role)

    /**
     * Delete Slope from repository.
     * 
     * @param slopeId id of Slope entity
     */
    def delete(slopeId:String) = repository.delete(slopeId)

    /**
     * Update Slope.
     * 
     * @param slope 
     */
    def update(slope:Slope) = repository.update(slope)

}
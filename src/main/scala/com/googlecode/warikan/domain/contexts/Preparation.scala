package com.googlecode.warikan.domain.contexts

import com.googlecode.warikan.domain.contexts.rootpopulaters._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._
import com.googlecode.warikan.domain.roles._
import com.googlecode.warikan.domain.roles.methodful._

/**
 * Preparation Context.
 * 
 * @author yukei
 */
class Preparation(private val name:String) {

    private val administrator:Administrator = new Slope(SlopeRepository.nextId, name) with SlopeAdministrator

    /** Slope entity. */
    val slope:Slope = administrator.asInstanceOf[Slope]

    /**
     * Add mapping of role name and weight.
     * 
     * @param role name of Role
     * @param weight weight of Role
     */
    def addMapping(role:Role, weight:AllotWeight) = 
        administrator.addMapping(role, weight)

    /**
     * Commit modification.
     */
    def commit = SlopeRepository.add(slope)

}
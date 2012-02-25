package com.googlecode.warikan.domain.roles

import com.googlecode.warikan.domain.models._

/**
 * Administrator methodless Role.
 * 
 * @author yukei
 */
trait Administrator {

    /**
     * Add role-weight mapping.
     * 
     * @param role name of role
     * @param weight weight of role
     */
    def addMapping(role:Role, weight:AllotWeight)

    /**
     * Update name of Slope.
     * 
     * @param slopeName
     */
    def updateName(slopeName:String)

    /**
     * Update weight map with specified map.
     * 
     * @param weights weight map
     */
    def updateWeights(weights:Map[Role, AllotWeight])

}
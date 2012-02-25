package com.googlecode.warikan.domain.roles

import com.googlecode.warikan.domain.shared.math._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

/**
 * PieMaker.
 * 
 * @author yukei
 */
trait PieMaker {

    /**
     * Create Pie to be used in allot calculation.
     * 
     * @param slopeId id of Slope entity
     * @return Pie(name of Participant -> Fraction)
     */
    def createPie(slopeId:String):Map[UserName, Fraction]

}
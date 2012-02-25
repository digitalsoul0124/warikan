package com.googlecode.warikan.domain.roles.methodful

import com.googlecode.warikan.domain.shared.math._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.roles._
import com.googlecode.warikan.domain.repositories._

/**
 * Slope Administrator.
 * 
 * @author yukei
 */
trait SlopeAdministrator extends Administrator {

    val id:String

    var name:String

    def put(entry:(Role, AllotWeight))

    /** @inheritDoc */
    def addMapping(role:Role, weight:AllotWeight) =  put(role -> weight)

    /** @inheritDoc */
    def updateName(slopeName:String) =  name = slopeName

    /** @inheritDoc */
    def updateWeights(weights:Map[Role, AllotWeight]) = {
        weights.foreach { it =>
            put(it)
        }
    }

}
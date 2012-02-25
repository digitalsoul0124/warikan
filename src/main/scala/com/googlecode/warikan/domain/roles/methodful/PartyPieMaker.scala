package com.googlecode.warikan.domain.roles.methodful

import com.googlecode.warikan.domain.shared.math._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._
import com.googlecode.warikan.domain.roles._

/**
 * Party Pie Maker.
 * 
 * Methodful Role of PieMaker.
 * 
 * @author yukei
 */
trait PartyPieMaker extends PieMaker {

    def participants:Map[UserName, Participant]

    /** @inheritDoc */
    def createPie(slopeId:String):Map[UserName, Fraction] = {
        val weightPie = createWeightPie(slopeId)
        val totalWeight = sumWeightUp(weightPie)
        createPercentagePie(weightPie, totalWeight)
    }

    private def createWeightPie(slopeId:String):Map[UserName, AllotWeight] = {
        var pie = Map[UserName, AllotWeight]()
        for (participant <- participants.values) {
            val weight = SlopeRepository.weightOf(slopeId, participant.role)
            pie += (participant.userName -> weight)
        }
        pie
    }

    private def sumWeightUp(weightPie:Map[UserName, AllotWeight]):Int =
        (0 /: weightPie.values) ((sum, weight) => sum + weight.weight)

    private def createPercentagePie(amountPie:Map[UserName, AllotWeight], 
            totalWeight:Int):Map[UserName, Fraction] = {    
        var percentagePie = Map[UserName, Fraction]()
        for (it <- amountPie.elements) {
            percentagePie += (it._1 -> new Fraction(it._2.weight, totalWeight))
        }
        percentagePie
    }

}
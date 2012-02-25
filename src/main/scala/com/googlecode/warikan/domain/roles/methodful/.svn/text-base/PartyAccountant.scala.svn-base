package com.googlecode.warikan.domain.roles.methodful

import com.googlecode.warikan.domain.shared.math._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.roles._

/**
 * Party Accountant.
 * 
 * Methodful Role of Accounting.
 * 
 * @author yukei
 */
trait PartyAccountant extends Accountant {

    var sum:Int

    def setAllots(allot:Map[UserName, Allot])

    /**
     * Calculate each participant Allot by specified Slope.
     * 
     * @param pieMaker PieMaker
     * @param slopeId ID of Slope entity to be used
     */
    def adjust(pieMaker:PieMaker, slopeId:String) = {
        val pie = pieMaker.createPie(slopeId)
        val allots = calculateAllot(pie) 
        setAllots(allots)
    }

    private def calculateAllot(pie:Map[UserName, Fraction]):Map[UserName, Allot] = {
        var tempAllot = Map[UserName, Allot]()
        for (it <- pie) {
            val name = it._1
            val fraction:Fraction = pie(name)
            val payment:Int = fraction * sum
            tempAllot += (name -> new Allot(name, payment))
        }
        tempAllot
    }

}
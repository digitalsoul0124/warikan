package com.googlecode.warikan.domain.roles.methodful

import com.googlecode.warikan.domain.roles._

/**
 * Party Payer.
 * 
 * Methodful Role of Payer.
 * 
 * @author yukei
 */
trait PartyPayer extends Payer {

    var sum:Int

    /**
     * Pay sum of the Party.
     * 
     * @param sum sum of the Party
     */
    def pay(sum:Int) = this.sum = sum

}
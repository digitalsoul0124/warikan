package com.googlecode.warikan.domain.roles

/**
 * Payer methodless Role.
 * 
 * @author yukei
 */
trait Payer {

    /**
     * Pay sum.
     * 
     * @param sum sum
     */
    def pay(sum:Int)

}
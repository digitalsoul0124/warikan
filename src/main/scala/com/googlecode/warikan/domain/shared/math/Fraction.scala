package com.googlecode.warikan.domain.shared.math

/**
 * Fraction.
 * 
 * @author yukei
 */
class Fraction(val numerator:Int, val denominator:Int) {

    /**
     * Execute multiplication.
     * 
     * @param operand Operand
     * @return Result of multiplication
     */
    def *(operand:Int):Int = {
        if (denominator != 0) {
            operand * numerator / denominator
        } else {
            0
        }
    }

}
package com.googlecode.warikan.domain.models

/**
 * Allot.
 * 
 * Represents Allot to each Participant.
 * 
 * @author yukei
 */
@serializable
class Allot(val userName:UserName, val amount:Int) {
    require(userName != null)
    require(amount >= 0)

    /**
     * Returns a string representation of Allot.
     * 
     * @return String Expression
     */
    override def toString:String = {
        val sb:StringBuilder = new StringBuilder
        sb.append("name:").append(userName.name).append(",")
        sb.append("amount:").append(amount)
        sb.toString
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * 
     * @param other Object with which to compare
     * @return true if this object equals other object
     */
    override def equals(other:Any):Boolean = {
        other match {
            case that:Allot =>
                (that canEqual this) &&
                userName == that.userName
            case _ => false
        }
    }

    /**
     * Indicates whether some other object is instance of Allot.
     * 
     * @param other object to be evaluated
     * @return true if other object is instance of Allot
     */
    def canEqual(other:Any):Boolean = {
        other.isInstanceOf[Allot]
    }

    /**
     * Returns a hash code value for the object.
     * 
     * @return hash code value
     */
    override def hashCode:Int = userName.hashCode

}
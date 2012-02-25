package com.googlecode.warikan.domain.models

/**
 * Participant.
 * 
 * Represents Participant of a Party.
 * 
 * @author yukei
 */
@serializable
class Participant(val userName:UserName, val role:Role) {
    require(userName != null)
    require(role != null)

    /**
     * Returns a string representation of Allot.
     * 
     * @return String Expression
     */
    override def toString:String = {
        val sb:StringBuilder = new StringBuilder
        sb.append("Participant:{")
        sb.append("name:").append(userName).append(",")
        sb.append("role:").append(role)
        sb.append("}")
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
            case that:Participant =>
                (that canEqual this) &&
                userName == that.userName
            case _ => false
        }
    }

    /**
     * Indicates whether some other object is instance of Participant.
     * 
     * @param other object to be evaluated
     * @return true if other object is instance of Allot
     */
    def canEqual(other:Any):Boolean = {
        other.isInstanceOf[Participant]
    }

    /**
     * Returns a hash code value for the object.
     * 
     * @return hash code value
     */
    override def hashCode:Int =  userName.hashCode

}
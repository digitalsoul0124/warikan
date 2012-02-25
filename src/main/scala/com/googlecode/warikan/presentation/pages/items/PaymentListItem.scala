package com.googlecode.warikan.presentation.pages.items

/**
 * Payment List Item.
 * 
 * @author yukei
 */
@serializable
class PaymentListItem {

    var name:String = _

    var role:String = _

    var payment:Int = 0

    override def toString:String = {
        val sb:StringBuilder = new StringBuilder
        sb.append("name:").append(name).append(",")
        sb.append("role:").append(role).append(",")
        sb.append("payment").append(payment)
        sb.toString
    }

}
package com.googlecode.warikan.presentation.pages

import java.text._
import java.util._

import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.basic._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.markup.html.list._
import org.apache.wicket.model._

import com.googlecode.warikan.application.actions._
import com.googlecode.warikan.presentation.pages.forms._
import com.googlecode.warikan.presentation.pages.items._

/**
 * Accounting page.
 * 
 * @author yukei
 */
class AccountingPage(val partyId:String) extends WebPage {

    val action:AccountingAction = new AccountingAction

    val mainFormModel = action.createMainFormModel(partyId)

    val slopeItems = action.createSlopeItems

    var paymentListItems = action.createPaymentListItems(partyId)

    // form:main
    val main = new Form("main", new CompoundPropertyModel(mainFormModel))
    add(main)

    // label:partyName
    main.add(new Label("partyName"))

    // label:schedule
    main.add(new Label("schedule"))

    // label:location
    main.add(new Label("location"))

    // label:sum
    main.add(new Label("sum"))

    // select:slopeId
    val slopeSelect = new DropDownChoice("selectedSlope", slopeItems)
    main.add(slopeSelect)

    // button:calculate
    val calculateButton = new Button("calculateButton") {
        override def onSubmit = {
            paymentListItems.clear
            val slopeId = mainFormModel.selectedSlope.id
            val newPaymentListItems = action.calculate(partyId, slopeId)
            paymentListItems.addAll(newPaymentListItems)
        }
    }
    main.add(calculateButton)

    // table:payments
    val paymentList = new ListView[PaymentListItem]("payments", paymentListItems) {
        override def populateItem(listItem:ListItem[PaymentListItem]) {
            
            // model
            val modelObject = listItem.getModelObject
            listItem.setModel(new CompoundPropertyModel(modelObject))
             
            // label
            listItem.add(new Label("name"))
            listItem.add(new Label("role"))
            listItem.add(new Label("payment"))
        }
    }
    main.add(paymentList)

    // button:back
    val backButton = new Button("backButton") {
        override def onSubmit = {
            setResponsePage(new TopPage)
        }
    }
    main.add(backButton)

    // button:commit
    val commitButton = new Button("commitButton") {
        override def onSubmit = {
            action.commit(partyId, paymentListItems)
            setResponsePage(new AccountingPage(partyId))
        }
    }
    main.add(commitButton)

}
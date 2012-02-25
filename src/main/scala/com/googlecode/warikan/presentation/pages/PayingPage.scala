package com.googlecode.warikan.presentation.pages

import java.text._

import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.basic._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.markup.html.list._
import org.apache.wicket.model._

import com.googlecode.warikan.application.actions._
import com.googlecode.warikan.presentation.pages.forms._
import com.googlecode.warikan.presentation.pages.items._

/**
 * Paying page.
 * 
 * @author yukei
 */
class PayingPage(val partyId:String) extends WebPage {

    private val action = new PayingAction

    private val mainFormModel = action.createMainForm(partyId)

    // form:main
    val main = new Form("main", new CompoundPropertyModel(mainFormModel))
    add(main)

    // text:sum
    main.add(new TextField("sum"))

    // label:partyName
    main.add(new Label("partyName"))

    // label:schedule
    main.add(new Label("schedule"))

    // label:location
    main.add(new Label("location"))

    // button:cancelButton
    val cancelButton = new Button("cancelButton") {
        override def onSubmit = {
            setResponsePage(new TopPage)
        }
    }
    main.add(cancelButton)

    // button:commitButton
    val commitButton = new Button("commitButton") {
        override def onSubmit = {
            action.pay(partyId, mainFormModel.sum)
            setResponsePage(new TopPage)
        }
    }
    main.add(commitButton)
}
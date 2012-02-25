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
 * Top page.
 * 
 * @author yukei
 */
class TopPage extends WebPage {

    val action = new TopAction

    val partyListItems = action.createPartyList

    // form:main
    val form = new Form("main")
    add(form)

    // button:slope
    val slopeButton = new Button("slope") {
        override def onSubmit = {
            setResponsePage(new SlopeListPage)
        }
    }
    form.add(slopeButton)

    // button:Planning
    val planningButton = new Button("planningButton") {
        override def onSubmit = {
            setResponsePage(new PlanningPage)
        }
    }
    form.add(planningButton)

    // table:parties
    val partyList = new ListView[PartyListItem]("parties", partyListItems) {
        
        override def populateItem(listItem:ListItem[PartyListItem]) = {
            
            // model object
            val modelObject = listItem.getModelObject
            listItem.setModel(new CompoundPropertyModel(modelObject))
            
            // label
            listItem.add(new Label("partyName"))
            listItem.add(new Label("schedule"))
            listItem.add(new Label("location"))
            listItem.add(new Label("sum"))
            
            // button:paying
            val payingButton = new Button("payingButton") {
                override def onSubmit = {
                    setResponsePage(new PayingPage(modelObject.partyId))
                }
            }
            listItem.add(payingButton)
            
            // button:accounting
            val accountingButton = new Button("accountingButton") {
                override def onSubmit = {
                    setResponsePage(new AccountingPage(modelObject.partyId))
                }
            }
            listItem.add(accountingButton)
            
            // button:edit
            val editButton = new Button("editButton") {
                override def onSubmit = {
                    setResponsePage(new PlanningPage(Option(modelObject.partyId)))
                }
            }
            listItem.add(editButton)
            
            // button:delete
            val deleteButton = new Button("deleteButton") {
                override def onSubmit = {
                    action.deleteParty(modelObject.partyId)
                    setResponsePage(new TopPage)
                }
            }
            listItem.add(deleteButton)

        }
        
    }
    form.add(partyList)

}
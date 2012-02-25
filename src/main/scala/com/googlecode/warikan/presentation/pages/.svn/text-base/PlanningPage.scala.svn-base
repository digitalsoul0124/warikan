package com.googlecode.warikan.presentation.pages

import java.text._
import java.util._

import com.google.inject._

import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.basic._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.markup.html.list._
import org.apache.wicket.model._

import com.googlecode.warikan.application.actions._
import com.googlecode.warikan.presentation.pages.forms._
import com.googlecode.warikan.presentation.pages.items._

/**
 * Planning page.
 * 
 * @author yukei
 */
class PlanningPage(partyIdOption:Option[String]) extends WebPage {

    def this() = this(None)

    val action = new PlanningAction(partyIdOption)

    val mainFormModel = action.createPlanningMainForm

    val participantListItems = action.createParticipantListItems

    val roles = action.createRoleList

    // form:main
    val main = new Form("main", new CompoundPropertyModel(mainFormModel))
    add(main)

    // text:name
    main.add(new TextField("partyName"))

    // text:schedule
    main.add(new TextField("schedule"))

    // text:location
    main.add(new TextField("location"))

    // table:participants
    val participants = new ListView[ParticipantListItem]("participants", participantListItems) {
        override def populateItem(listItem:ListItem[ParticipantListItem]) = {
            listItem.setModel(new CompoundPropertyModel(listItem.getModelObject))
            listItem.add(new TextField("name"))
            listItem.add(new DropDownChoice("role", roles))
        }
    }
    main.add(participants)

    // button:newLineButton
    val newLineButton = new Button("newLineButton") {
        override def onSubmit = {
            participantListItems.add(new ParticipantListItem)
        }
    }
    main.add(newLineButton)

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
            action.commit(mainFormModel, participantListItems)
            setResponsePage(new TopPage)
        }
    }
    main.add(commitButton)

}
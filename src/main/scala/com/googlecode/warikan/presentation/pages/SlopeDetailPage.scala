package com.googlecode.warikan.presentation.pages

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
 * SlopeDetail page.
 * 
 * @author yukei
 */
class SlopeDetailPage(val slopeId:Option[String]) extends WebPage {

    def this() = this(None)

    val action = new SlopeDetailAction(slopeId)

    val mainFormModel = action.createSlopeDetailMainForm

    val weightListItems = action.createWeightListItems

    val roleValues = action.createRoleValues

    val weightValues = new ArrayList[String]
    for (val i <- 1 to 20) {
        weightValues.add(0, i.toString)
    }

    // form:main
    val main = new Form("main", new CompoundPropertyModel(mainFormModel))
    add(main)

    // text:slopeName
    main.add(new TextField("slopeName"))

    // button:newLine
    val newLineButton = new Button("newLineButton") {
        override def onSubmit = {
            weightListItems.add(new WeightListItem)
        }
    }
    main.add(newLineButton)

    // table:weights
    val weights = new ListView[WeightListItem]("weights", weightListItems) {
        override def populateItem(listItem:ListItem[WeightListItem]) = {
            listItem.setModel(new CompoundPropertyModel(listItem.getModelObject))
            listItem.add(new DropDownChoice("role", roleValues))
            listItem.add(new DropDownChoice("weight", weightValues))
        }
    }
    main.add(weights)

    // button:commit
    val commitButton = new Button("commitButton") {
        override def onSubmit = {
            action.commit(mainFormModel, weightListItems)
            setResponsePage(new SlopeListPage)
        }
    }
    main.add(commitButton)

    // button:cancel
    val cancelButton = new Button("cancelButton") {
        override def onSubmit = {
            setResponsePage(new SlopeListPage)
        }
    }
    main.add(cancelButton)

}
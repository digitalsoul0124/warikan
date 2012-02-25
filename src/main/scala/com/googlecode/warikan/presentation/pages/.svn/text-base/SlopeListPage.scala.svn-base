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
 * SlopeList page.
 * 
 * @author yukei
 */
class SlopeListPage extends WebPage {

    val action = new SlopeListAction

    var slopeListItems = action.createSlopeListItems

    val main = new Form("main")
    add(main)

    // table:slopes
    val slopeList = new ListView[SlopeListItem]("slopes", slopeListItems) {
        override def populateItem(listItem:ListItem[SlopeListItem]) = {
            val modelObject:SlopeListItem = listItem.getModelObject
            listItem.setModel(new CompoundPropertyModel(modelObject))
            
            // number column
            listItem.add(new Label("number"))
            
            // slopeName column
            listItem.add(new Label("slopeName"))
            
            // edit button
            val editButton = new Button("editButton") {
                override def onSubmit = {
                    setResponsePage(new SlopeDetailPage(Option(modelObject.slopeId)))
                }
            }
            listItem.add(editButton)
            
            // delete button
            val deleteButton = new Button("deleteButton") {
                override def onSubmit = {
                    action.delete(modelObject.slopeId)
                    setResponsePage(new SlopeListPage)
                }
            }
            listItem.add(deleteButton)
        }
    }
    main.add(slopeList)

    // button:newSlopeButton
    val newSlopeButton = new Button("newSlopeButton") {
        override def onSubmit = {
            setResponsePage(new SlopeDetailPage)
        }
    }
    main.add(newSlopeButton)

    // button:Back
    val backButton = new Button("backButton") {
        override def onSubmit = {
            setResponsePage(new TopPage)
        }
    }
    main.add(backButton)

}
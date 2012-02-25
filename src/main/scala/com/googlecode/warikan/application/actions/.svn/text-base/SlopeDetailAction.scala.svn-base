package com.googlecode.warikan.application.actions

import java.util.ArrayList

import scala.collection.JavaConversions._

import com.googlecode.warikan.presentation.pages.forms._
import com.googlecode.warikan.presentation.pages.items._
import com.googlecode.warikan.domain.contexts._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

/**
 * SlopeDetail Action.
 * 
 * Action class for SlopeDetail page.
 * 
 * @author yukei
 */
@serializable
class SlopeDetailAction(private val slopeId:Option[String]) {

    private var slopeOption:Option[Slope] = None
    if (slopeId.isDefined) {
        slopeOption = SlopeRepository.forSlopeId(slopeId.get)
    }

    /**
     * Commit modification to Slope.
     * 
     * @param mainFormModel Main form for SlopeDetail page
     * @param weightListItems Items in Weight list
     */
    def commit(mainFormModel:SlopeDetailMainForm, weightListItems:java.util.List[WeightListItem]) = {
        if (slopeOption.isEmpty) { // prepare
            executePreparation(mainFormModel, weightListItems)
        } else { // edit
            executeEdit(mainFormModel, weightListItems)
        }
    }
    
    /**
     * Execute preparation.
     * 
     * @param mainForm
     * @param weightListItems
     */
    private def executePreparation(mainForm:SlopeDetailMainForm, weightListItems:java.util.List[WeightListItem]) = {
        
        val preparation:Preparation = new Preparation(mainForm.slopeName)
        
        weightListItems.foreach { weightListItem =>
            val role:Role = Role(weightListItem.role)
            val weight:AllotWeight = AllotWeight(weightListItem.weight.toInt)
            preparation.addMapping(role, weight)
        }
        
        preparation.commit
    }

    /**
     * Update Slope according to input information.
     * 
     * @param mainFormModel
     * @param weightListItems
     */
    private def executeEdit(mainForm:SlopeDetailMainForm, weightListItems:java.util.List[WeightListItem]) = {
        
        val edit:EditSlope = new EditSlope(slopeId.get)
        
        // update name
        edit.updateName(mainForm.slopeName)
        
        // update weights according to weightListItems
        var weights = Map[Role, AllotWeight]()
        weightListItems.foreach { items =>
            weights += Role(items.role) -> AllotWeight(items.weight.toInt)
        }
        edit.updateWeights(weights)
        
        // commit
        edit.commitEdit
    }

    /**
     * Create MainForm for SlopeDetail page.
     * 
     * @return MainForm for SlopeDetail page
     */
    def createSlopeDetailMainForm:SlopeDetailMainForm = {
        val mainForm = new SlopeDetailMainForm
        if (slopeOption.isDefined) populateMainForm(mainForm, slopeOption.get)
        mainForm
    }

    /**
     * Create list of WeightListItem.
     * 
     * @return list of WeightListItem
     */
    def createWeightListItems:java.util.List[WeightListItem] = {
        val weightListItems = new ArrayList[WeightListItem]
        if (slopeOption.isDefined) {
            val slope:Slope = slopeOption.get
            slope.weights.foreach { it =>
                val item = new WeightListItem
                item.role = it._1.name
                item.weight = it._2.weight.toString
                weightListItems.add(item)
            }
        }
        weightListItems
    }

    /**
     * Create list of Role name.
     * 
     * @return list of Role name
     */
    def createRoleValues:java.util.List[String] = {
        val roleValues = new ArrayList[String]
        RoleRepository.forAll.foreach { role => 
            roleValues.add(role.name)
        }
        roleValues
    }
    
    private def populateMainForm(mainForm:SlopeDetailMainForm, slope:Slope) = {
        mainForm.slopeId = slope.id
        mainForm.slopeName = slope.name
    }

}
package com.googlecode.warikan.application.actions

import java.util.ArrayList

import com.googlecode.warikan.presentation.pages.items._
import com.googlecode.warikan.domain.contexts._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

/**
 * SlopeList Action.
 * 
 * Action class for SlopeList page.
 * 
 * @author yukei
 */
@serializable
class SlopeListAction {
    
    /**
     * Delete Slope.
     * 
     * @param slopeId ID for Slope entity to be deleted
     */
    def delete(slopeId:String) = new EditSlope(slopeId).delete

    /**
     * Create list of SlopeListItem.
     * 
     * @return list of SlopeListItem
     */
    def createSlopeListItems:java.util.List[SlopeListItem] = {
        var slopeListItems = new ArrayList[SlopeListItem]
        var number:Int = 1
        for (slope <- SlopeRepository.forAll) {
            slopeListItems.add(convertToSlopeListItem(slope, number))
            number += 1
        }
        slopeListItems
    }

    private def convertToSlopeListItem(slope:Slope, number:Int):SlopeListItem = {
        val slopeListItem = new SlopeListItem
        slopeListItem.number = number.toString
        slopeListItem.slopeId = slope.id.toString
        slopeListItem.slopeName = slope.name
        slopeListItem
    }
}
package com.googlecode.warikan.application.actions

import java.text._
import java.util.ArrayList
import java.util.Date

import scala.collection.JavaConversions._

import com.googlecode.warikan.presentation.pages.forms._
import com.googlecode.warikan.presentation.pages.items._
import com.googlecode.warikan.domain.contexts._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

/**
 * Planning Action.
 * 
 * Action class for Planning page.
 * 
 * @author yukei
 */
@serializable
class PlanningAction(private val partyIdOption:Option[String]) {

    private var partyOption:Option[Party] = None
    if (partyIdOption.isDefined) {
        partyOption = PartyRepository.forPartyId(partyIdOption.get)
    }

    private val format = new SimpleDateFormat("yyyy/MM/dd")

    /**
     * Commit modification to Party.
     * 
     * @param mainFormModel Main form for Planning page
     * @param participantListItems Items in Participant list
     */
    def commit(mainFormModel:PlanningMainForm, participantListItems:java.util.List[ParticipantListItem]) = {
        if (partyOption.isEmpty) { // planning
            executePlanning(mainFormModel, participantListItems)
        } else { // edit
            executeEdit(mainFormModel, participantListItems)
        }
    }

    /**
     * Execute planning.
     * 
     * @param mainForm
     * @param participantListItems
     */
    private def executePlanning(mainForm:PlanningMainForm, participantListItems:java.util.List[ParticipantListItem]) = {
        
        val planning:Planning = new Planning
        
        // set basic information
        val date:Date = format.parse(mainForm.schedule)
        val info = PartyInformation(mainForm.partyName, date, mainForm.location)
        planning.setup(info)
        
        // set participant information
        participantListItems.foreach { item => 
            planning.addPerticipant(UserName(item.name), Role(item.role))
        }
        
        // commit modification
        planning.commit
    }

    /**
     * Edit party entity.
     * 
     * @param mainForm
     * @param participantListItems
     */
    private def executeEdit(mainForm:PlanningMainForm, participantListItems:java.util.List[ParticipantListItem]) = {
        
        val editParty:EditParty = new EditParty(partyIdOption.get)
        
        // update party info
        val partyName = mainForm.partyName
        val schedule = format.parse(mainForm.schedule)
        val location = mainForm.location
        val info = PartyInformation(partyName, schedule, location)
        editParty.updatePartyInfo(info)

        // update participants
        var participants = Map[UserName, Role]()
        participantListItems.foreach { participantListItem =>
            participants += UserName(participantListItem.name) -> Role(participantListItem.role)
        }
        editParty.updateParticipant(participants)
        
        // clear Allot
        editParty.clearAllot
        
        // commit modification
        editParty.commitEdit
    }

    /**
     * Create MainForm for Planning page.
     * 
     * @return MainForm for Planning page
     */
    def createPlanningMainForm:PlanningMainForm = {
        val mainForm = new PlanningMainForm
        if (partyOption.isDefined) populateMainForm(mainForm, partyOption.get)
        mainForm
    }

    /**
     * Create list of ParticipantListItem
     * 
     * @return list of ParticipantListItem
     */
    def createParticipantListItems:java.util.List[ParticipantListItem] = {
        val participantListItems = new ArrayList[ParticipantListItem]
        if (partyOption.isDefined) {
            val party:Party = partyOption.get
            party.participants.values.foreach { participant =>
                participantListItems.add(convertToParticipantListItem(participant))
            }
        }
        participantListItems
    }

    /**
     * Create Role list.
     * 
     * @return list of role name
     */
    def createRoleList:java.util.List[String] = {
        val roles = new ArrayList[String]
        RoleRepository.forAll.foreach { role =>
            roles.add(role.name)
        }
        roles
    }

    private def populateMainForm(mainForm:PlanningMainForm, party:Party) = {
        mainForm.partyId = party.id
        mainForm.partyName = party.info.name
        mainForm.schedule = format.format(party.info.schedule)
        mainForm.location = party.info.location        
    }

    private def convertToParticipantListItem(participant:Participant):ParticipantListItem = {
        val participantListItem = new ParticipantListItem
        participantListItem.name = participant.userName.name
        participantListItem.role = participant.role.name
        participantListItem
    }

}
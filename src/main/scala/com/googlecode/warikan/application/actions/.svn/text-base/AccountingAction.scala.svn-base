package com.googlecode.warikan.application.actions

import java.text._
import java.util.ArrayList

import org.apache.log4j.Logger

import scala.collection.JavaConversions._

import com.googlecode.warikan.presentation.pages.forms._
import com.googlecode.warikan.presentation.pages.items._
import com.googlecode.warikan.domain.contexts._
import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.domain.repositories._

/**
 * Accounting Action.
 * 
 * Action class for Accounting page.
 * 
 * @author yukei
 */
@serializable
class AccountingAction {

    @transient
    private val logger:Logger = Logger.getLogger(classOf[AccountingAction])

    private val format = new SimpleDateFormat("yyyy/MM/dd")

    /**
     * Execute calculation.
     * 
     * Execute adjust in Accounting context and create paymentListItems from party.
     * Result of this method is not committed.
     * 
     * @param partyId ID of Party
     * @param slopeId ID of Slope to be used
     * @return PaymentListItems reflecting allots in Party entity 
     */
    def calculate(partyId:String, slopeId:String):java.util.List[PaymentListItem] = {
        
        val accounting:Accounting = new Accounting(partyId)
        
        accounting.adjustBy(slopeId)
        
        createPaymentListItemsOfParty(accounting.party)
    }

    /**
     * Commit calculation.
     * 
     * Update allots in Party entity and execute commit.
     * 
     * @param partyId ID of Party entity
     * @param paymentListItems list of PaymentListItem
     */
    def commit(partyId:String, paymentListItems:java.util.List[PaymentListItem]) = {
        
        logger.info("paymentListItems:" + paymentListItems)
        
        val accounting:Accounting = new Accounting(partyId)
        
        accounting.clearAllots
        
        // set payment of each participants according to list item
        paymentListItems.foreach { paymentListItem =>
            accounting.setPaymentOfParticipant(UserName(paymentListItem.name), paymentListItem.payment)
        }
        
        accounting.commit
    }

    /**
     * Create Main Form for Accounting page.
     * 
     * @param partyId ID for Party entity.
     * @return Main Form for Accounting page
     */
    def createMainFormModel(partyId:String):AccountingMainForm = {
        val party:Party = new Accounting(partyId).party
        convertToFormModel(party)
    }

    /**
     * Create list of SlopeItem.
     * 
     * @return list of SlopeItem
     */
    def createSlopeItems:java.util.List[SlopeItem] = {
        val slopeItems = new ArrayList[SlopeItem]
        SlopeRepository.forAll.foreach { slope =>
            slopeItems.add(convertToSlopeItem(slope))
        }
        slopeItems
    }

    /**
     * Create list of PaymentListItem
     * 
     * @param partyId ID for Party entity
     * @return list of PaymentListItem
     */
    def createPaymentListItems(partyId:String):java.util.List[PaymentListItem] = {
        val accounting:Accounting = new Accounting(partyId)
        val party:Party = accounting.party
        createPaymentListItemsOfParty(party)
    }

    private def convertToFormModel(party:Party):AccountingMainForm = {
        val mainFormModel = new AccountingMainForm
        mainFormModel.partyName = party.info.name
        mainFormModel.schedule = format.format(party.info.schedule)
        mainFormModel.location = party.info.location
        mainFormModel.sum = party.sum.toString
        mainFormModel
    }

    private def convertToSlopeItem(slope:Slope):SlopeItem = {
        val slopeItem = new SlopeItem
        slopeItem.id = slope.id
        slopeItem.name = slope.name
        slopeItem
    }

    private def convertToPaymentListItem(participant:Participant, payment:Int):PaymentListItem = {
        val paymentListItem = new PaymentListItem
        paymentListItem.name = participant.userName.name
        paymentListItem.role = participant.role.name
        paymentListItem.payment  = payment
        paymentListItem
    }

    private def createPaymentListItemsOfParty(party:Party):java.util.List[PaymentListItem] = {
        val paymentListItems = new ArrayList[PaymentListItem]()
        party.participants.values.foreach { participant =>
            val paymentListItem:PaymentListItem = convertToPaymentListItem(participant, party.paymentOf(participant.userName))
            paymentListItems.add(paymentListItem)
        }
        paymentListItems
    }
}
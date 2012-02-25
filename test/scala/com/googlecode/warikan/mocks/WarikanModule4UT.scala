package com.googlecode.warikan.mocks

import com.google.inject._

import com.googlecode.warikan.infrastructure.repositories._

class WarikanModule4UT extends AbstractModule {
	
	override def configure = {
		bind(classOf[PartyRepositoryImpl]).to(classOf[MockPartyRepositoryImpl])
		bind(classOf[RoleRepositoryImpl]).to(classOf[MockRoleRepositoryImpl])
		bind(classOf[SlopeRepositoryImpl]).to(classOf[MockSlopeRepositoryImpl])
	}

}
package com.googlecode.warikan.mocks

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.infrastructure.repositories._

class MockRoleRepositoryImpl extends RoleRepositoryImpl {
	
	val thisRoles = List[Role](
			new Role("Chief"), 
			new Role("Manager"), 
			new Role("Member"), 
			new Role("Novice"))
			
	override def forAll:List[Role] = thisRoles

}
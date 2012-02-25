package com.googlecode.warikan.domain.repositories

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.infrastructure.inject._
import com.googlecode.warikan.infrastructure.repositories._

/**
 * Role Repository.
 * 
 * @author yukei
 */
object RoleRepository {

    /** Implementation of Role repository. */
    val repository:RoleRepositoryImpl = Injector.getInstance(classOf[RoleRepositoryImpl])

    /** 
     * Search for all Role.
     * 
     * @return list of Role
     */
    def forAll:List[Role] = repository.forAll

}
package com.googlecode.warikan.mocks

import com.googlecode.warikan.domain.models._
import com.googlecode.warikan.infrastructure.repositories._

class MockSlopeRepositoryImpl extends SlopeRepositoryImpl {

	var slopes = Map[String, Slope]()
	
	override def nextId:String = (slopes.size + 1).toString
	
	override def add(slope:Slope) = slopes += (slope.id -> slope)
			
	override def forSlopeId(id:String):Option[Slope] = {
		if (slopes.contains(id)) Option(slopes(id)) else None 
	}
	
	override def forAll:List[Slope] = slopes.values.toList
	
	override def weightOf(slopeId:String, role:Role):AllotWeight = { 
	    var result:AllotWeight = AllotWeight(0);
	    slopes(slopeId).weights.foreach { it =>
	        if (it._1 == role) result = it._2
	    }
	    result
	}
	
	override def delete(slopeId:String) = slopes -= slopeId
	
	def initialize = slopes = Map[String, Slope]()
	
}
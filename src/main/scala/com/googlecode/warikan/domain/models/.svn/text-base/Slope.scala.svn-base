package com.googlecode.warikan.domain.models

/**
 * Slope.
 * 
 * @author yukei
 */
@serializable
class Slope(val id:String, var name:String) {

    private var _weights = Map[Role, AllotWeight]()

    def this(id:String) = this(id, null)

    /**
     * Put weight entry.
     * 
     * @param taple(Role, weight)
     */
    def put(entry:(Role, AllotWeight)) = _weights += entry

    /**
     * Get Weight map.
     * 
     * @param Weight map
     */
    def weights:Map[Role, AllotWeight] = _weights 

    /**
     * Populate this object with another Slope.
     * 
     * @param another another Slope
     */
    def populateWith(another:Slope) = {
        this.name = another.name
        this._weights = another._weights
    }

}
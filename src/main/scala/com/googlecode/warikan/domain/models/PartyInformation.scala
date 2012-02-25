package com.googlecode.warikan.domain.models

import java.util.Date

@serializable
case class PartyInformation(
        
        /** Name of Party */
        name:String,

        /** Schedule */
        schedule:Date,

        /** Location */
        location:String
        
    )
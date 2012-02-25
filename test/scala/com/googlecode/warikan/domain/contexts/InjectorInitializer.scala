package com.googlecode.warikan.domain.contexts

import org.junit._

import com.googlecode.warikan.infrastructure.inject._

import com.googlecode.warikan.mocks._

trait InjectorInitializer {
    
    var initialized:Boolean = false
    
    @Before
    def beforeClass = {
        if (!initialized) {
            Injector.config_=(new WarikanModule4UT)
            initialized = true
        }
    }

}
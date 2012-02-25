package com.googlecode.warikan.presentation.web

import org.apache.wicket.protocol.http.WebApplication

import com.googlecode.warikan.infrastructure.inject._
import com.googlecode.warikan.presentation.pages.TopPage

/**
 * Warikan Application.
 * 
 * @author yukei
 */
class WarikanApplication extends WebApplication {

    def getHomePage = classOf[TopPage]

    override def init() = {
        super.init
        
        // configure html file location
        val resourceSettings = getResourceSettings
        resourceSettings.addResourceFolder("WEB-INF/pages")
        val locator = new HtmlFileLocator("com/googlecode/warikan/presentation/pages")
        resourceSettings.setResourceStreamLocator(locator)
        
        // initialize injector
        Injector.config_=(new WarikanModule)
    }

}
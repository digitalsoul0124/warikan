package com.googlecode.warikan.presentation.web

import org.apache.wicket.util.resource._
import org.apache.wicket.util.resource.locator._

/**
 * Html File Locator.
 * 
 * @author yukei
 */
class HtmlFileLocator(val pagesPath:String) extends ResourceStreamLocator {

    override def locate(clazz:Class[_], path:String):IResourceStream = {
        var targetPath:String = path
        if(path.indexOf(pagesPath) != -1) {
            targetPath = path.substring(pagesPath.length() + 1)
        }
        super.locate(clazz, targetPath)
    }

}
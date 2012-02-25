package com.googlecode.warikan.infrastructure.inject

import com.google.inject._

/**
 * Injector.
 * 
 * @author yukei
 */
object Injector {

    private var _config:AbstractModule = _

    /**
     * Set configuration.
     * 
     * @param config Configuration
     */
    def config_= (config:AbstractModule) { _config = config }

    /**
     * Get instance of specified class.
     * 
     * @param clazz Class to get instance
     * @return T instance of specified class
     */
    def getInstance[T](clazz:Class[T]):T = {
        Guice.createInjector(_config).getInstance(clazz)
    }

}
package com.backmarket.pocnavigation.core

import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition

/**
 * Returns an instance of [T] if handled by Koin di.
 *
 * This method is particularly useful when you want to get an instance of any class (through Koin di)
 * without subclass the calling class/interface with the [KoinComponent].
 */
inline fun <reified T> getFromDI(
    noinline parameters: ParametersDefinition? = null
): T = GlobalContext.get().get(parameters = parameters)

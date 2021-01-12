package com.backmarket.pocnavigation.navigation.direction

import android.app.Activity
import androidx.fragment.app.Fragment
import com.backmarket.pocnavigation.core.getFromDI
import com.backmarket.pocnavigation.navigation.ktx.EmptyNavContext
import org.koin.core.parameter.parametersOf

typealias Screen = Any

/**
 * Base interface representing a "direction" to go.
 */
interface NavDirection

/**
 * Gets the implementation for a given [NavDirection].
 */
@Suppress("SpreadOperator")
inline fun <reified T : NavDirection> getNavImpl(vararg param: Any?): T =
    getFromDI {
        parametersOf(*param)
    }

/**
 * Direction representing a screen.
 *
 * Used in the case we want to navigate to an other screen of the application.
 */
interface ScreenDirection : NavDirection {

    /**
     * Context for the screen direction.
     */
    val navContext: NavDirectionContext
        get() = EmptyNavContext

    /**
     * Screen to navigate to.
     *
     * It could be an [Activity] or a [Fragment] for example.
     */
    fun toScreen(): Screen
}

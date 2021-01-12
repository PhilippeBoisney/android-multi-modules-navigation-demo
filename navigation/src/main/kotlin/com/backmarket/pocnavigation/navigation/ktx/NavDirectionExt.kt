package com.backmarket.pocnavigation.navigation.ktx

import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.backmarket.pocnavigation.core.illegal
import com.backmarket.pocnavigation.navigation.direction.NavDirection
import com.backmarket.pocnavigation.navigation.direction.NavDirectionContext
import com.backmarket.pocnavigation.navigation.direction.ScreenDirection

internal fun NavDirection.isIntent() =
    (this as? ScreenDirection)?.toScreen() is Intent

internal fun NavDirection.isFragment() =
    (this as? ScreenDirection)?.toScreen() is Fragment

internal fun NavDirection.isFragmentDialog() =
    (this as? ScreenDirection)?.toScreen() is DialogFragment

/**
 * Requires the [Intent] from the receiver [NavDirection].
 *
 * Also, this method will automatically set some params to the [Intent].
 */
fun NavDirection.requireIntent() =
    when (this) {
        is ScreenDirection -> toIntentScreen {
            setNavContextParam(this@requireIntent)
        }
        else -> null
    } ?: illegal("This direction doesn't support Intent")

/**
 * Requires the [Fragment] from the receiver [NavDirection].
 *
 * Also, this method will automatically set some params to the [Bundle] of the required [Fragment].
 */
fun NavDirection.requireFragment(): Fragment =
    when (this) {
        is ScreenDirection -> toFragmentScreen {
            arguments = (arguments ?: bundleOf()).run {
                setNavContextParam(this@requireFragment)
            }
        }
        else -> null
    } ?: illegal("This direction doesn't support Fragment")

/**
 * Requires the [DialogFragment] from the receiver [NavDirection].
 *
 * Also, this method will automatically set some params to the [Bundle] of the required [Fragment].
 */
fun NavDirection.requireDialogFragment(): DialogFragment =
    requireFragment() as DialogFragment

// region Utils

private fun ScreenDirection.toIntentScreen(block: Intent.() -> Unit): Intent? =
    (toScreen() as? Intent)?.apply(block)

private fun ScreenDirection.toFragmentScreen(block: Fragment.() -> Unit): Fragment? =
    (toScreen() as? Fragment)?.apply(block)

private fun Bundle.setNavContextParam(direction: ScreenDirection) = apply {
    putParcelable(NavDirectionContext.CONTEXT_PARAM, direction.navContext)
}

private fun Intent.setNavContextParam(direction: ScreenDirection) = apply {
    direction.navContext
        .takeIf { it != EmptyNavContext }
        ?.let { putExtra(NavDirectionContext.CONTEXT_PARAM, direction.navContext) }
}

// endregion

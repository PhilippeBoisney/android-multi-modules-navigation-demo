package com.backmarket.pocnavigation.navigation

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import androidx.core.app.NavUtils
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.backmarket.pocnavigation.core.illegal
import com.backmarket.pocnavigation.core.observeAndConsumeNonNull
import com.backmarket.pocnavigation.core.unsupported
import com.backmarket.pocnavigation.navigation.direction.NavDirection
import com.backmarket.pocnavigation.navigation.dsl.doWhen
import com.backmarket.pocnavigation.navigation.ktx.getFragmentTag
import com.backmarket.pocnavigation.navigation.ktx.requireDialogFragment
import com.backmarket.pocnavigation.navigation.ktx.requireIntent
import com.backmarket.pocnavigation.navigation.ktx.startForResultCodeWithData
import com.backmarket.pocnavigation.navigation.model.NavigationCommand
import com.backmarket.pocnavigation.navigation.model.NavigationResult
import com.backmarket.pocnavigation.navigation.model.NavigationResult.Companion.RESULT_PARAM
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * This interface is used to centralize & encapsulate the navigation logic.
 * Mainly implemented by [Fragment] or [FragmentActivity]
 */
interface Navigable {

    /**
     * Convenient method that will be used by any [Fragment] or [FragmentActivity]
     * to properly observe the [NavigableViewModel.router].
     *
     * Set [delegate] if you want to delegate the navigation actions to another [Navigable].
     */
    fun <T : NavDirection> NavigableViewModel<T>.observeNavigation(delegate: Navigable? = null) {
        router.observeAndConsumeNonNull(getLifecycleOwner()) {
            val navigable = delegate ?: this@Navigable
            it.handleNavigation(navigableScope, navigable)
        }
    }

    /**
     * Convenient method which help the [NavigableViewModel.router] to navigate to a screen.
     */
    fun navigateTo(direction: NavDirection) {
        direction.doWhen {
            isFragmentDialog {
                navigateToDialogFragment(requireDialogFragment())
            }
            isFragment {
                illegal("You should manually implement 'navigateTo()' if you want to navigate to a Fragment.")
            }
            isIntent {
                navigateToActivity(requireIntent())
            }
            otherDirection { unsupported() }
        }
    }

    /**
     * Convenient method which help the [NavigableViewModel.router] to navigate to a screen
     * and expecting a [NavigationResult] returned by it.
     */
    suspend fun navigateToForResult(
        direction: NavDirection,
        continuation: Continuation<NavigationResult>
    ) {
        coroutineScope {
            direction.doWhen {
                isFragment {
                    illegal("navigateToForResult() is only available from [Fragment] & [FragmentActivity]")
                }
                isIntent {
                    launch {
                        val result = navigateToActivityForResult(requireIntent())
                        continuation.resume(
                            NavigationResult(code = result.first, data = result.second)
                        )
                    }
                }
                otherDirection { unsupported() }
            }
        }
    }

    /**
     * Convenient method which help the [NavigableViewModel.router] to navigate to a previous screen.
     * This method will be used mainly through a funnel.
     *
     * Note that this method will not working with any shared transition (expect if you override it).
     */
    fun navigateToPrevious(direction: NavDirection) {
        direction.doWhen {
            isFragment {
                illegal("You should manually implement 'navigateToPrevious()' to navigate to a previous Fragment.")
            }
            isIntent {
                NavUtils.navigateUpTo(requireActivity(), requireIntent())
            }
            otherDirection { unsupported() }
        }
    }

    /**
     * Convenient method which help the [NavigableViewModel.router] to navigate back
     * (for example, when user clicks on the back button).
     */
    fun navigateBack() {
        requireActivity().onBackPressed()
    }

    /**
     * Convenient method which help the [NavigableViewModel.router] to finish the current screen.
     * (for example, when user clicks on any close button)
     */
    fun navigateFinish(results: Any?) {
        requireActivity().run {
            results?.let {
                val parcelables = when (it) {
                    is Parcelable -> listOf(it)
                    is List<*> -> it.filterIsInstance<Parcelable>()
                    else -> illegal(
                        errorMessage = "You should pass to the finish() method " +
                                "only 'Parcelable' or 'List<Parcelable>' variable types."
                    )
                }
                val intent =
                    Intent().putParcelableArrayListExtra(RESULT_PARAM, ArrayList(parcelables))
                setResult(Activity.RESULT_OK, intent)
            }
            finishAfterTransition()
        }
    }

    // region Utils

    private fun NavigationCommand.handleNavigation(
        scope: CoroutineScope,
        navigable: Navigable
    ) {
        when (this) {
            is NavigationCommand.To -> navigable.navigateTo(direction)
            is NavigationCommand.ToPrevious -> navigable.navigateToPrevious(direction)
            is NavigationCommand.ForResult -> scope.launch { navigable.navigateToForResult(direction, continuation) }
            is NavigationCommand.Back -> navigable.navigateBack()
            is NavigationCommand.Finish -> navigable.navigateFinish(results)
        }
    }

    private fun getLifecycleOwner() = when (this) {
        is FragmentActivity -> this
        is Fragment -> viewLifecycleOwner
        else -> illegal(CONTEXT_ERROR)
    }

    private fun requireActivity(): Activity = when (this) {
        is FragmentActivity -> this
        is Fragment -> this.requireActivity()
        else -> illegal(CONTEXT_ERROR)
    }

    private fun navigateToActivity(intent: Intent) = when (this) {
        is FragmentActivity -> startActivity(intent)
        is Fragment -> startActivity(intent)
        else -> illegal(CONTEXT_ERROR)
    }

    private suspend fun navigateToActivityForResult(intent: Intent) = when (this) {
        is FragmentActivity -> startForResultCodeWithData(intent)
        is Fragment -> startForResultCodeWithData(intent)
        else -> illegal("navigateToForResult() is only available from [Fragment] & [FragmentActivity]")
    }

    private fun navigateToDialogFragment(dialogFragment: DialogFragment) {
        val fragmentManager = when (this) {
            is FragmentActivity -> supportFragmentManager
            is Fragment -> parentFragmentManager
            else -> illegal(CONTEXT_ERROR)
        }
        fragmentManager.beginTransaction().run {
            dialogFragment.show(this, dialogFragment.getFragmentTag())
        }
    }

    companion object {
        private const val CONTEXT_ERROR =
            "This context is not handled... The Navigable interface supports only [Fragment] & [FragmentActivity]" +
                    " (or [ContextAware] objects if they only want to launch an activity)"
    }

    // endregion
}

package com.backmarket.pocnavigation.navigation.ktx

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.florent37.inlineactivityresult.kotlin.InlineActivityResultException
import com.github.florent37.inlineactivityresult.kotlin.coroutines.startForResult

fun Fragment.getFragmentTag(): String = javaClass.name

/**
 * Starts an activity with [intent] and returns a [Pair] :
 * - first: Result code
 * - second: [Intent]
 */
internal suspend fun FragmentActivity.startForResultCodeWithData(intent: Intent): Pair<Int, Intent?> {
    return try {
        val result = startForResult(intent)
        Pair(result.resultCode, result.data)
    } catch (e: InlineActivityResultException) {
        Pair(Activity.RESULT_CANCELED, null)
    }
}

/**
 * Starts a fragment with [intent] and returns a [Pair] :
 * - first: Result code
 * - second: [Intent]
 */
internal suspend fun Fragment.startForResultCodeWithData(intent: Intent): Pair<Int, Intent?> {
    return try {
        val result = startForResult(intent)
        Pair(result.resultCode, result.data)
    } catch (e: InlineActivityResultException) {
        Pair(Activity.RESULT_CANCELED, null)
    }
}

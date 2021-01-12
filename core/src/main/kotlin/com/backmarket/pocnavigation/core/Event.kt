package com.backmarket.pocnavigation.core

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 *
 * https://medium.com/google-developers/livedata-with-snackbar-navigation
 * -and-other-events-the-singleliveevent-case-ac2622673150
 */
class Event<out T>(
    private val content: T? = null
) {

    var consumed = false
        private set // Allow external read but not write

    /**
     * Consumes the content if it's not been consumed yet.
     * @return The unconsumed content or `null` if it was consumed already.
     */
    fun consume(): T? {
        return if (consumed) {
            null
        } else {
            consumed = true
            content
        }
    }

    /**
     * Consumes the content if it's not been consumed yet and run the block [block].
     */
    fun consumeAndRun(block: (T?) -> Unit) {
        if (!consumed) {
            block(consume())
        }
    }

    fun consumeAndRunNonNull(block: (T) -> Unit) {
        consumeAndRun { if (it != null) block(it) }
    }

    /**
     * @return The content whether it's been handled or not.
     */
    fun peek(): T? = content

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Event<*>

        if (content != other.content) return false
        if (consumed != other.consumed) return false

        return true
    }

    override fun hashCode(): Int {
        var result = content?.hashCode() ?: 0
        result = 31 * result + consumed.hashCode()
        return result
    }
}

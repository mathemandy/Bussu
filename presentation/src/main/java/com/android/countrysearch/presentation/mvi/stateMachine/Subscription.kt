package com.android.countrysearch.presentation.mvi.stateMachine

import com.android.countrysearch.presentation.mvi.base.DispatchIntent
import com.android.countrysearch.presentation.mvi.base.NoOpIntentDispatcher
import com.android.countrysearch.presentation.mvi.base.ScreenState
import com.android.countrysearch.presentation.mvi.base.StateTransform
import com.android.countrysearch.presentation.mvi.base.Subscriber
import com.android.countrysearch.presentation.mvi.base.ViewState

internal class Subscription<S : ScreenState, V : ViewState>(
    private var subscriber: Subscriber<V>?,
    private val transform: StateTransform<S, V>
) {

    private var oldState: V? = null

    fun updateState(state: S) {
        val newState: V = transform(state)
        if (oldState == null || oldState != newState) {
            subscriber?.onNewState(newState)
            oldState = newState
        }
    }

    fun onIntentDispatch(dispatchIntent: DispatchIntent) {
        subscriber?.dispatchIntent = dispatchIntent
    }

    fun dispose() {
        subscriber?.dispatchIntent = NoOpIntentDispatcher
        subscriber = null
    }
}

package com.android.countrysearch.presentation_android

import androidx.annotation.UiThread
import com.android.countrysearch.presentation.mvi.base.DispatchIntent
import com.android.countrysearch.presentation.mvi.base.Subscriber
import com.android.countrysearch.presentation.mvi.base.ViewIntent
import com.android.countrysearch.presentation.mvi.base.ViewState

/**
 * Represents a basic UI component that can be part of a screen
 */
abstract class UIComponent<ComponentState : ViewState> : Subscriber<ComponentState> {

    @UiThread
    abstract fun render(state: ComponentState)

    @UiThread
    protected fun sendIntent(intent: ViewIntent) {
        dispatchIntent.invoke(intent)
    }

    override var dispatchIntent: DispatchIntent = NoOpIntentDispatcher

    override fun onNewState(state: ComponentState) {
        render(state)
    }
}

private val NoOpIntentDispatcher: DispatchIntent
    get() = {}

abstract class StatelessUIComponent : UIComponent<ViewState>() {
    override fun render(state: ViewState) {}
}

package com.android.countrysearch.presentation.mvi.stateMachine

import com.android.countrysearch.presentation.mvi.base.IntentProcessor
import com.android.countrysearch.presentation.mvi.base.NoOpIntent
import com.android.countrysearch.presentation.mvi.base.ScreenState
import com.android.countrysearch.presentation.mvi.base.StateTransform
import com.android.countrysearch.presentation.mvi.base.Subscriber
import com.android.countrysearch.presentation.mvi.base.ViewIntent
import com.android.countrysearch.presentation.mvi.base.ViewResult
import com.android.countrysearch.presentation.mvi.base.ViewState
import com.android.countrysearch.presentation.mvi.base.ViewStateReducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.scan

public abstract class StateMachine<S : ScreenState, R : ViewResult>(
    private val intentProcessor: IntentProcessor<R>,
    private val reducer: ViewStateReducer<S, R>,
    initialState: S,
    initialIntent: ViewIntent = NoOpIntent,
    config: Config = NoOpConfig
) {

    private val mainScope: CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val intents: Channel<ViewIntent> = Channel<ViewIntent>(1).apply {
        offer(initialIntent)
    }

    private val subscriberDelegate = SubscriptionDelegate(mainScope, initialState)

    init {
        intents.receiveAsFlow()
            .filter { it !is NoOpIntent }
            .mapConfig(config, intentProcessor::intentToResult)
            .scan(initialState, reducer::reduce)
            .distinctUntilChanged()
            .onEach(subscriberDelegate::updateState)
            .launchIn(mainScope)
    }

    @Suppress("UNCHECKED_CAST")
    public fun <V : ViewState> subscribe(
        subscriber: Subscriber<V>,
        transform: StateTransform<S, V>
    ) {
        subscriberDelegate.subscribe(
            subscriber as Subscriber<ViewState>,
            transform as StateTransform<ScreenState, ViewState>,
            intents::offer
        )
    }

    public fun unSubscribe() {
        mainScope.cancel()
        unSubscribeComponents()
    }

    public fun unSubscribeComponents() {
        subscriberDelegate.unSubscribe()
    }
}

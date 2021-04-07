package com.android.countrysearch.presentation.mvi.base

public interface ViewIntent
internal object NoOpIntent : ViewIntent

public typealias DispatchIntent = (ViewIntent) -> Unit

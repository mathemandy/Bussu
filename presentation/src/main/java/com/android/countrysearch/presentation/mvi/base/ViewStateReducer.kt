package com.android.countrysearch.presentation.mvi.base

public interface ViewStateReducer<S : ScreenState, R : ViewResult> {
    public fun reduce(oldState: S, result: R): S
}

package com.android.countrysearch.remote.base

public interface RemoteModelMapper<in M, out E> {

    public fun mapFromModel(model: M): E

    public fun mapModelList(models: List<M>): List<E> {
        return models.mapTo(mutableListOf(), ::mapFromModel)
    }

    public fun safeString(value: String?) : String = value ?: "N/A"



}

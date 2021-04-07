package com.android.countrysearch.cache.base

interface CacheModelMapper<M, E> {

    fun mapToModel(entity: E): M

    fun mapToEntity(model: M): E
}

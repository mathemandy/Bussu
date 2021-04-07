package com.android.countrysearch.presentation.mapper

public interface ModelMapper<M, D> {

    public fun mapToModel(domain: D): M
    public fun mapToDomain(model: M): D

    public fun mapToModelList(domainList: List<D>): List<M> {
        return domainList.mapTo(mutableListOf(), ::mapToModel)
    }

    public fun mapToDomainList(modelList: List<M>): List<D> {
        return modelList.mapTo(mutableListOf(), ::mapToDomain)
    }

    public fun safeString(value: String?) : String = value ?: "N/A"


}

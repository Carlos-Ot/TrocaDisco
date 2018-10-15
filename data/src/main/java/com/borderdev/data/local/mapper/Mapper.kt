package com.borderdev.data.local.mapper

interface Mapper<E, D> {
    fun toDomain(entity: E): D
    fun fromDomain(domain: D): E
}
package com.borderdev.core.extensions

fun <T> List<T>.union(list1: List<T>, list2: List<T>): List<T> {
    val mutableList: MutableList<T> = mutableListOf()

    mutableList.addAll(list1)
    mutableList.addAll(list2)

    return mutableList.toList()
}
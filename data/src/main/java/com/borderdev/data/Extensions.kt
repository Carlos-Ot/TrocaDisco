package com.borderdev.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Suppress("NOTHING_TO_INLINE")
inline fun <T : Enum<T>> T.toInt(): Int = this.ordinal

inline fun <reified T : Enum<T>> Int.toEnum(): T = enumValues<T>()[this]

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)
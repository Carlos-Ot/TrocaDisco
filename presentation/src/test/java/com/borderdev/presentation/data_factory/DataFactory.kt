package com.borderdev.presentation.data_factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object DataFactory {

    fun randomString(): String = UUID.randomUUID().toString()

    fun randomInt(): Int = ThreadLocalRandom.current().nextInt(0, 200)

    fun randomLong(): Long = randomInt().toLong()

    fun randomBoolean(): Boolean = Math.random() < 0.5

    fun randomDate(): String = Date(randomLong()).toString()

    fun randomUrl(): String = "http://trocaodisco.com.br/" + randomString()

    fun randomPath(): String = "/trocaodisco/episodes" + randomString()
}
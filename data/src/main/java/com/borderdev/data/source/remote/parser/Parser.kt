package com.borderdev.data.source.remote.parser

interface Parser<in R, out M> {

    fun parse(remote: R): M
}
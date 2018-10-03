package com.borderdev.data.remote.parser

interface Parser<in R, out M> {

    fun parse(remote: R): M
}
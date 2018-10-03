package com.borderdev.data.remote.network.entity

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "rss")
data class Feed(
//        @Attribute(name = "version")
//        val version: Float,

        @Element( name = "channel")
        val channel: Channel
)
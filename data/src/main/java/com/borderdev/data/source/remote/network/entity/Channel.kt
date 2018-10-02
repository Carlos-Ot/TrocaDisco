package com.borderdev.data.source.remote.network.entity

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "channel")
data class Channel(

        @Element(name = "item")
        val items: List<Item>
)
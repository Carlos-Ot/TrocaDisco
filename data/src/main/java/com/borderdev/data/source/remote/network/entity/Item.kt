package com.borderdev.data.source.remote.network.entity

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "item")
data class Item (
        @PropertyElement(name = "title")
        val title: String,

        @PropertyElement(name = "link")
        val link: String,

        @PropertyElement(name = "pubDate")
        val pubDate: String,

        @Element(name = "category")
        val categories: List<RemoteCategory>,

        @PropertyElement(name = "description")
        val description: String,

        @PropertyElement(name = "content:encoded")
        val content: String
)
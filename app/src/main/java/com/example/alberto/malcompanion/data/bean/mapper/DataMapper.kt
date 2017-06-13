package com.example.alberto.malcompanion.data.bean.mapper

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "entry", strict = false)
class DataMapper {
    @get: Element(name = "title") var title:String?= null
}


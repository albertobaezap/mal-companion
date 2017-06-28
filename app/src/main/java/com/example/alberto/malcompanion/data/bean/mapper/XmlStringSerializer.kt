package com.example.alberto.malcompanion.data.bean.mapper

import android.util.Xml
import java.io.StringWriter

class XmlStringSerializer {

   fun updateAnime(episode: Int): String {
      val xmlSerializer = Xml.newSerializer()
      val stringWriter = StringWriter()

      xmlSerializer.setOutput(stringWriter)

      //Start document
      xmlSerializer.startDocument("UTF-8", true)
      xmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
      xmlSerializer.startTag("", "entry")
      xmlSerializer.startTag("", "episode")
      xmlSerializer.text(episode.toString())
      xmlSerializer.endTag("", "episode")
      xmlSerializer.endTag("", "entry")
      xmlSerializer.endDocument()

      return stringWriter.toString()

   }
}

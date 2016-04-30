package docxToTexConverter

import java.nio.file.Files
import java.nio.file.Paths
import java.io.{ IOException, FileOutputStream, FileInputStream, File, FileNotFoundException }
import java.util.zip.{ ZipEntry, ZipInputStream }
import scala.io.Source
import scala.collection.mutable.Map

class DocxAnalyser (implicit folder: File){




  def getInfo : Array[String] = {
    val key = Array("Pages", "Words", "Characters", "Paragraphs", "CharactersWithSpaces")
    val value = new Array[String](5)
    val filename = folder.getPath + "/docProps/app.xml"
    val line = Source.fromFile(filename).mkString
    for (x <- key) {
      val start = line.indexOf("<"+x+">")+x.length+2
      val end = line.indexOf("</"+x+">")
      value(key.indexOf(x)) = line.substring(start, end)
      println(value(key.indexOf(x)))
    }
    value
  }

  def getMath : Unit = {
  	val math = new Math()
  }
}

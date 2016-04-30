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
    key.foreach { x => println }
    val value = new Array[String](5)
    val filename = folder.getPath + "/docProps/app.xml"
    val line = scala.xml.XML.loadFile(filename)

    for (x <- 0 to key.length) {
      value(x) = (line \ key(x)).text
    }
    value
  }
}

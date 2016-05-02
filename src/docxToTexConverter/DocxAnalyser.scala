package docxToTexConverter

import java.nio.file.Files
import java.nio.file.Paths
import java.io.{ IOException, FileOutputStream, FileInputStream, File, FileNotFoundException }
import java.util.zip.{ ZipEntry, ZipInputStream }
import scala.io.Source
import scala.collection.mutable.Map
import scala.xml._

class DocxAnalyser (folder: File){

	val metadata = folder.getPath + "/docProps/app.xml"
	val metadata2 = folder.getPath + "/docProps/core.xml"
	val document = folder.getPath + "/word/document.xml"

  def getInfo : Map[String, String] = {
    val map : Map[String, String] = Map()

    val key = Array("Pages", "Words", "Characters", "Paragraphs", "CharactersWithSpaces")
    val line = XML.loadFile(metadata)

    for (x <- key) {
      map put (x, (line \ x).text)
    }

    val key2 = Array("Creator", "Created")
    val line2 = XML.loadFile(metadata2)

    for (x <- key2) {
      map put (x, (line2 \ x).text)
    }

    map
  }

  def getMath : Unit = {
  	val math = new Math(XML.loadFile(document))
  }
}
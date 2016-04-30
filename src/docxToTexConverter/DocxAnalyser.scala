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
	val document = folder.getPath + "/word/document.xml"


  def getInfo : Array[String] = {
    val key = Array("Pages", "Words", "Characters", "Paragraphs", "CharactersWithSpaces")
    key.foreach { x => println }
    val value = new Array[String](5)

    val line = XML.loadFile(metadata)

    for (x <- key) {
      value(key.indexOf(x)) = (line \ x).text
    }
    value
  }

  def getMath : Unit = {
  	val math = new Math(XML.loadFile(document))
  }
}

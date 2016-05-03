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
	/**
	 * @return A hashMap with Pages, Words, Characters, Paragraphs, CharactersWithSpaces, Creator and Created.
	 */
  def getInfo : Map[String, String] = {
    val map : Map[String, String] = Map()

    //Retrieves all the valuable data from /docProps/app.xml
    val key = Array("Pages", "Words", "Characters", "Paragraphs", "CharactersWithSpaces")
    val line = XML.loadFile(metadata)
    key.map { x =>  map put (x, (line \ x).text)}

    //Retrieves all the valuable data from /docProps/core.xml
    val key2 = Array("Creator", "Created")
    val line2 = XML.loadFile(metadata2)
    key2.map { x =>  map put (x, (line2 \ x).text)}

    map
  }

  def getMath : Unit = {
  	val math = new Math("XML.loadFile(document)")
  }
}
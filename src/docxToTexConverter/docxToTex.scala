package docxToTexConverter

import java.io.{ IOException, FileOutputStream, FileInputStream, File }
import java.util.zip.{ ZipEntry, ZipInputStream }
import javafx.stage.FileChooser

object docxToTex {
  def main(args:Array[String]): Unit = {
    if (args.length != 1)
      throw new IllegalArgumentException("There isn't exactly one agrument")
    
    implicit val input = new File(args(0))
    println(input.getPath);
    if (!input.exists()) 
      throw new IllegalArgumentException("File doesn't exist")
    
    val uz = new Unzipper(input)
    uz.run
    uz.delete
  }
}
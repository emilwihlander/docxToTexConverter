package docxToTexConverter

import java.io.{ IOException, FileOutputStream, FileInputStream, File }
import java.util.zip.{ ZipEntry, ZipInputStream }
import javafx.stage.FileChooser

object docxToTex {
  def main(args:Array[String]): Unit = {
    if (args.length != 1)
      throw new IllegalArgumentException("There isn't exactly one agrument")

    val input = new File(args(0))
    println(input.getAbsolutePath);
    if (!input.exists()) //Checks if the file exists
      throw new IllegalArgumentException("File doesn't exist")
    if (!input.getPath.endsWith(".docx")) //Checks if the file is a .docx file
      throw new IllegalArgumentException("File name doesn't end with .docx")

    val uz = new Unzipper(input)
    val folder = uz.run //Unzips input and saves the unzip-folder path in folder
    val da = new DocxAnalyser(folder)
    da.getInfo
  }
}
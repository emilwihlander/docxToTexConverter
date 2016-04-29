package docxToTexConverter

import java.io.File

object docxToTex {
  def main(args:Array[String]): Unit = {
    if (args.length != 1)
      throw new IllegalArgumentException("There isn't exactly one agrument")
    
    
    println(args(0))
    
  }
}
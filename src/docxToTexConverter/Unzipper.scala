package docxToTexConverter

import java.nio.file.Files
import java.nio.file.Paths
import java.io.{ IOException, FileOutputStream, FileInputStream, File }
import java.util.zip.{ ZipEntry, ZipInputStream }

class Unzipper (val input: File){
  def run : File = {
    val outputFolder = chooseFolder
    val buffer = new Array[Byte](1024)
    	
    try{
    	//create output directory is not exists
    	outputFolder.mkdir()
    		
    	//get the zip file content
    	val zis = new ZipInputStream(new FileInputStream(input));
    	//get the zipped file list entry
    	var ze = zis.getNextEntry();
    		
    	while(ze!=null){
    	  val fileName = ze.getName()
        val newFile = new File(outputFolder + File.separator + fileName)
                
        println("file unzip : "+ newFile.getAbsoluteFile())
            
        //create all non exists folders
        //else you will hit FileNotFoundException for compressed folder
        new File(newFile.getParent()).mkdirs()
              
        val fos = new FileOutputStream(newFile)          

        var len = zis.read(buffer)
        while (len > 0) {
          fos.write(buffer, 0, len)
          len = zis.read(buffer)
        }
        
        fos.close()
        ze = zis.getNextEntry()
    	}
    	
      zis.closeEntry()
    	zis.close()
    } catch {
      case ex : IOException => {
       ex.printStackTrace(); 
      }
    }
    outputFolder
  }
  
  private def chooseFolder : File = { //Chooses a folder that doesn't exist
    var path = input.getPath.substring(0,input.getPath.length-5) //Removes .docx from string
    var i = 1
    while(Files.isDirectory(Paths.get(path))) {
      path = path + "(" + i + ")"
      i += 1
    }
    new File(path)
  }
}
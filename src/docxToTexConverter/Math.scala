package docxToTexConverter

import java.io.{ IOException, FileOutputStream, FileInputStream, File, FileNotFoundException }
import scala.io.Source
import scala.xml._
import java.util.{ArrayList, Scanner}
import scala.util.matching.Regex

class Math (document : String) {
	val equations = new ArrayList()

	def run : Unit = {
		val pattern = new Regex("\\A<m:t>")
		val scanner = new Scanner(new File(document))
		println((pattern findAllIn document).mkString(","))
		while(scanner.hasNext()){
			println(scanner.next())
		}
	}

	def getEquations : Unit = {

	}
}

object testaro {
	def main(args: Array[String]): Unit = {
		val test = new Math("C:/Users/sn4ilz/Documents/GitHub/docxToTexConverter/dummy/word/document.xml")
		test.run
	}
}

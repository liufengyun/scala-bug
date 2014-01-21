import scala.io._
import java.io._

object Main {
  def main(args: Array[String]) {
    val ss = Source.fromFile("data.bin")
    println("Scala:" + ss.next.toInt)
    ss.close

    val bis = new BufferedInputStream(new FileInputStream("data.bin"))
    println("Java:" + bis.read)
    bis.close
  }
}


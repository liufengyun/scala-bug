# Scala-Bug

This program demonstrates a bug of Scala standard library [scala.io.Source](http://www.scala-lang.org/api/current/index.html#scala.io.Source) in reading binary files.

## Description

In the file `data.bin`, it contains the hexidecimal `0xea`, which is `11101010` in binary and should be converted to `234` in decimal.

The `main.scala` file contain two ways to read the file:

``` Scala
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
```

When I run `scala main.scala`, the program outputs follows:

```
Scala:205
Java:234
```

The Java library generates correct output, while the Scala library not.

## How to run

Just run `scala main.scala`.

## How to solve the problem

Well, there seems to be no good library in Scala to read binary files. However, there's a way to make the example above work by setting encoding of the file to `ISO-8859-1` as follows:

``` Scala
val ss = Source.fromFile("data.bin", "ISO-8859-1")
```

It looks strange to set encoding for reading binary files, but [scala.io.Source](http://www.scala-lang.org/api/current/index.html#scala.io.Source) is designed for text files. It's a pity that Scala doesn't provide a convenient library for reading binary files.

## Tips

You can use `hexdump data.bin` or `xxd data.bin` to view the contents of `data.bin`.


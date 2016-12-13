import scalanative.native._, stdlib._, stdio._

object Test {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    import java.nio.file._
    import attribute.BasicFileAttributes

    Files.walkFileTree(Paths.get("/home/gui/treetest"), new SimpleFileVisitor[Path] {
      override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult = {
        println(dir)
        println(attrs)
        FileVisitResult.CONTINUE
      }
      override def visitFile(file: Path,
                             attrs: BasicFileAttributes): FileVisitResult = {
        println(file)
        println(attrs)
        FileVisitResult.CONTINUE
      }
    })

  }
}

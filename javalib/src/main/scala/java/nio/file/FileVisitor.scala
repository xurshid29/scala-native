package java.nio.file

trait FileVisitor[T] {
  def postVisitDirectory(x$1: Path,x$2: java.io.IOException): FileVisitResult = ???
  def preVisitDirectory(x$1: Path,x$2: attribute.BasicFileAttributes): FileVisitResult = ???
  def visitFile(x$1: Path,x$2: attribute.BasicFileAttributes): FileVisitResult = ???
  def visitFileFailed(x$1: Path,x$2: java.io.IOException): FileVisitResult = ???
}

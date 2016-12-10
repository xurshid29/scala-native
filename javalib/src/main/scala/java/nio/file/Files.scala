package java.nio
package file

import charset.Charset

object Files {
  // def exists(path: Path): Boolean = ???
  // def readAllLines(src: Path): java.util.List[String] = ???
  // def write(dst: Path, src: Array[Byte], options: OpenOption*): Path = ???
  // def write(dst: Path, src: Iterable[_ <: CharSequence], charset: OpenOption*): Path = ???
  // def write(dst: Path, src: Iterable[_ <: CharSequence], charset: Charset, options: OpenOption*): Path = ???

  def walkFileTree(root: Path, fileVisitor: FileVisitor[_ >: Path]): Path = {

    // DEPTH FIRST SEARCH

    root
  }
}

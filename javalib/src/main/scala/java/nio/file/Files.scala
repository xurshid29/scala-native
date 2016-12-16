package java
package nio
package file

import io.IOException
import util.concurrent.TimeUnit._

import charset.Charset
import attribute.{FileTime, BasicFileAttributes}

import scalanative.native._
import scalanative.posixx._, FTW._, NFTW._

object Paths {
  def get(x: String, rest: Array[String]): Path = ???
}

object Files {
  private var fv: FileVisitor[_ >: Path] = null

  // java.nio.file.Files$::
  //   walkFileTree_trait.
  //   java.nio.file.Path_trait.
  //   java.nio.file.FileVisitor_trait
  //   java.nio.file.Path

  def walkFileTree(root: Path, fileVisitor: FileVisitor[_ >: Path]): Path = {
    fv = fileVisitor

    def convert(visitResult: FileVisitResult): Int = {
      import FileVisitResult._

      visitResult match {
        case CONTINUE      => FTW_CONTINUE
        case SKIP_SIBLINGS => FTW_SKIP_SIBLINGS
        case SKIP_SUBTREE  => FTW_SKIP_SUBTREE
        case TERMINATE     => FTW_STOP
      }
    }

    val fun: FunctionPtr4[CString, Ptr[stat], Int, Ptr[FTW], Int] =
      (fileBuffer: CString, sb: Ptr[stat], flag: Int, s: Ptr[FTW]) => {
        val path: Path = ???

        def attributes = new BasicFileAttributes {
          val fileKey: Object = (!sb).st_ino.asInstanceOf[Object]

          val isDirectory = flag == FTW_F
          val isRegularFile = flag == FTW_D
          val isSymbolicLink = flag == FTW_SL
          val isOther = !isDirectory && !isRegularFile && !isSymbolicLink

          val creationTime = FileTime.from((!sb).st_ctime_nsec.toLong, NANOSECONDS)
          val lastAccessTime = FileTime.from((!sb).st_atime_nsec.toLong, NANOSECONDS)
          val lastModifiedTime = FileTime.from((!sb).st_mtime_nsec.toLong, NANOSECONDS)

          val size = (!sb).st_size
        }

        val visitResult =
          flag match {
            case `FTW_F`   => fv.visitFile(path, attributes)
            case `FTW_D`   => fv.preVisitDirectory(path, attributes)
            case `FTW_SL`  => fv.visitFile(path, attributes)
            case `FTW_DNR` => fv.postVisitDirectory(path, new IOException("cannot read from directory"))
            case `FTW_NS`  => fv.postVisitDirectory(path, new IOException("cannot stat inode"))
            case `FTW_DP`  => fv.postVisitDirectory(path, new IOException("visiting a symbolic link with FTW_PHYS flag set")) // should not happen
            case `FTW_SLN` => fv.visitFileFailed(path, new IOException("invalid symlink"))
            case _         => fv.postVisitDirectory(path, new IOException(s"unkown flag: $flag"))
          }
        convert(visitResult)
      }

    nftw(
      toCString(root.toString),
      fun,
      1024,
      FTW_ACTIONRETVAL
    )

    root
  }
}

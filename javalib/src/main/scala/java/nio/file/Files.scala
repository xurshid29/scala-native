package java
package nio
package file

import io.IOException
import util.concurrent.TimeUnit._

import charset.Charset
import attribute.{FileTime, BasicFileAttributes}

import scalanative.native._
import scalanative.posix._
import scalanative.posix.ftw._

object Files {
  def walkFileTree(root: Path, fileVisitor: FileVisitor[_ >: Path]): Path = {
    import fileVisitor._


    def convert(visitResult: FileVisitResult): Int = {
      import FileVisitResult._

      visitResult match {
        case CONTINUE      => FTW_CONTINUE
        case SKIP_SIBLINGS => FTW_SKIP_SIBLINGS
        case SKIP_SUBTREE  => FTW_SKIP_SUBTREE
        case TERMINATE     => FTW_STOP
      }
    }

    ntfw(
      root.toAbsolutePath,
      (file, sb, flag, s) => {
        val path = new Path{ 
          def toAbsolutePath = (fromCString(file + s.base))
        }
        
        def attributes = new BasicFileAttributes {
          val fileKey = sb.st_ino  
          
          val isDirectory = flag == FTW_F
          val isRegularFile = flag == FTW_D 
          val isSymbolicLink = flag == FTW_SL
          val isOther = !isDirectory && !isRegularFile && !isSymbolicLink
          
          val creationTime = FileTime.from(sb.st_ctime_nsec, NANOSECONDS)
          val lastAccessTime = FileTime.from(sb.st_atime_nsec, NANOSECONDS)
          val lastModifiedTime = FileTime.from(sb.st_mtime_nsec, NANOSECONDS)

          val size = sb.size
        }

        val visitResult =
          flag match { 
            case `FTW_F`   => visitFile(path, attributes)
            case `FTW_D`   => preVisitDirectory(path, attributes)
            case `FTW_SL`  => visitFile(path, attributes)
            case `FTW_DNR` => postVisitDirectory(path, new IOException("cannot read from directory"))
            case `FTW_NS`  => postVisitDirectory(path, new IOException("cannot stat inode"))
            case `FTW_DP`  => postVisitDirectory(path, new IOException("visiting a symbolic link with FTW_PHYS flag set")) // should not happen
            case `FTW_SLN` => visitFileFailed(path, new IOException("invalid symlink"))
            case _ => postVisitDirectory(path, new IOException(s"unkown flag: $flag"))
          }
        convert(visitResult)
      },
      20,
      FTW_ACTIONRETVAL     
    )

    root
  }
}

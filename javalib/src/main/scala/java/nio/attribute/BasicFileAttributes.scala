package java.nio.file
package attribute

trait BasicFileAttributes {
  def creationTime(): FileTime = ???
  def fileKey(): Object = ???
  def isDirectory(): Boolean = ???
  def isOther(): Boolean = ???
  def isRegularFile(): Boolean = ???
  def isSymbolicLink(): Boolean = ???
  def lastAccessTime(): java.nio.file.attribute.FileTime = ???
  def lastModifiedTime(): java.nio.file.attribute.FileTime = ???
  def size(): Long = ???
}

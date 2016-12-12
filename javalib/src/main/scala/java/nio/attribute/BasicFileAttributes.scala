package java.nio.file
package attribute

trait BasicFileAttributes {
  def fileKey(): Object

  def isDirectory(): Boolean
  def isOther(): Boolean
  def isRegularFile(): Boolean
  def isSymbolicLink(): Boolean

  def creationTime(): FileTime
  def lastAccessTime(): FileTime
  def lastModifiedTime(): FileTime

  def size(): Long
}

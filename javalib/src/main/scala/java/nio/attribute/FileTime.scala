package java.nio.file
package attribute

import java.util.concurrent.TimeUnit

object FileTime {
  def from(value: Long, unit: TimeUnit): FileTime =
    new FileTime()
  // def from(instant: java.time.Instant): FileTime
}

class FileTime(private[FileTime] millis: Long) {
  def to(unit: TimeUnit): Long = ???
  def toMillis: Long =  millis
  // def toInstant: java.time.Instant
}

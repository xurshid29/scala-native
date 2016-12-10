package java.nio.file
package attribute

import java.util.concurrent.TimeUnit

object FileTime {
  def from(value: Long, unit: TimeUnit): FileTime =
    new FileTime()
  // def from(instant: java.time.Instant): FileTime
}

class FileTime(private[FileTime] value: Long, unit: TimeUnit) {
  def to(toUnit: TimeUnit): Long = unit.convert(value, toUnit) 
  def toMillis: Long =  unit.toMillis(value)
  // def toInstant: java.time.Instant
}

package scala.scalanative
package posixx

import native._

@struct class stat private(
  val st_dev: ULong,
  val st_ino: ULong,
  val st_mode: UInt,
  val st_nlink: UInt,
  val st_uid: UInt,
  val st_gid: UInt,
  val st_rdev: ULong,
  __pad1: ULong,
  val st_size: Long,
  val st_blksize: Int,
  __pad2: Int,
  val st_blocks: Long,
  val st_atime: Long,
  val st_atime_nsec: ULong,
  val st_mtime: Long,
  val st_mtime_nsec: ULong,
  val st_ctime: Long,
  val st_ctime_nsec: ULong,
  __unused4: UInt,
  __unused5: UInt
)

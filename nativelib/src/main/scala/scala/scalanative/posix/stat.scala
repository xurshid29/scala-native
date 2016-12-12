package scala.scalanative
package posix

import native._


@struct class stat private(
  st_dev: ULong,
  st_ino: ULong,
  st_mode: UInt,
  st_nlink: UInt,
  st_uid: UInt,
  st_gid: UInt,
  st_rdev: ULong,
  __pad1: ULong,
  st_size: Long,
  st_blksize: Int,
  __pad2: Int,
  st_blocks: Long,
  st_atime: Long,
  st_atime_nsec: ULong,
  st_mtime: Long,
  st_mtime_nsec: ULong,
  st_ctime: Long,
  st_ctime_nsec: ULong,
  __unused4: UInt,
  __unused5: UInt
)

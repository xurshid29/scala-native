package scala.scalanative
package native

import scala.language.dynamics
import scala.reflect.ClassTag
import runtime.{undefined, PrimitivePtr}

/** The C `const T *` pointer. */
final class Ptr[T] private (private val ptr: PrimitivePtr) extends AnyVal {

  /** Dereference a pointer. */
  @inline final def unary_!(implicit ct: ClassTag[T]): T = undefined

  /** Store a value to the address pointed at by a pointer. */
  @inline final def `unary_!_=`(value: T)(implicit ct: ClassTag[T]): Unit = undefined

  /** Compute a derived pointer by adding given offset. */
  @inline final def +(offset: Word)(implicit ct: ClassTag[T]): Ptr[T] = undefined

  /** Compute a derived pointer by substricting given offset. */
  @inline final def -(offset: Word)(implicit ct: ClassTag[T]): Ptr[T] = undefined

  /** Read a value at given offset. Equivalent to !(offset + word). */
  @inline final def apply(offset: Word)(implicit ct: ClassTag[T]): T = undefined

  /** Store a value to given offset. Equivalent to !(offset + word) = value. */
  @inline final def update(offset: Word, value: T)(implicit ct: ClassTag[T]): T = undefined
}

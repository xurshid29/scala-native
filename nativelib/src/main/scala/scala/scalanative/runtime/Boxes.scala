package scala.scalanative
package runtime

import native._

object Boxes {
  @inline def boxToUByte(v: UByte): Any   = v
  @inline def boxToUShort(v: UShort): Any = v
  @inline def boxToUInt(v: UInt): Any     = v
  @inline def boxToULong(v: ULong): Any   = v
  @inline def boxToPtr(v: Ptr[_]): Any    = v

  @inline def unboxToUByte(o: java.lang.Object): UByte =
    if (o == null) 0.toUByte
    else o.asInstanceOf[UByte]
  @inline def unboxToUShort(o: java.lang.Object): UShort =
    if (o == null) 0.toUShort
    else o.asInstanceOf[UShort]
  @inline def unboxToUInt(o: java.lang.Object): UInt =
    if (o == null) 0.toUInt
    else o.asInstanceOf[UInt]
  @inline def unboxToULong(o: java.lang.Object): ULong =
    if (o == null) 0.toULong
    else o.asInstanceOf[ULong]
  @inline def unboxToPtr(o: java.lang.Object): Ptr[_] =
    if (o == null) 0L.cast[Ptr[_]]
    else o.asInstanceOf[Ptr[_]]
}

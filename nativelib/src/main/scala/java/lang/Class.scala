package java.lang

import scalanative.native.Ptr
import scalanative.runtime.{Array => _, _}

final class _Class[A](val info: Ptr[Info]) {
  def cast(obj: Object): A = ???

  def getComponentType(): _Class[_] = {
    if (info == infoof[BooleanArray]) classOf[scala.Boolean]
    else if (info == infoof[CharArray]) classOf[scala.Char]
    else if (info == infoof[ByteArray]) classOf[scala.Byte]
    else if (info == infoof[ShortArray]) classOf[scala.Short]
    else if (info == infoof[IntArray]) classOf[scala.Int]
    else if (info == infoof[LongArray]) classOf[scala.Long]
    else if (info == infoof[FloatArray]) classOf[scala.Float]
    else if (info == infoof[DoubleArray]) classOf[scala.Double]
    else classOf[java.lang.Object]
  }

  def getInterfaces(): Array[_Class[_]] = ???

  def getName(): String =
    info.name

  def getSimpleName(): String =
    getName.split('.').last.split('$').last

  def getSuperclass(): Class[_ >: A] = ???

  def isArray(): scala.Boolean =
    (info == infoof[BooleanArray] ||
      info == infoof[CharArray] ||
      info == infoof[ByteArray] ||
      info == infoof[ShortArray] ||
      info == infoof[IntArray] ||
      info == infoof[LongArray] ||
      info == infoof[FloatArray] ||
      info == infoof[DoubleArray] ||
      info == infoof[ObjectArray])

  def isAssignableFrom(that: Class[_]): scala.Boolean = ???

  def isInstance(obj: Object): scala.Boolean = ???

  def isInterface(): scala.Boolean = ???

  def isPrimitive(): scala.Boolean =
    (info == infoof[PrimitiveBoolean] ||
      info == infoof[PrimitiveChar] ||
      info == infoof[PrimitiveByte] ||
      info == infoof[PrimitiveShort] ||
      info == infoof[PrimitiveInt] ||
      info == infoof[PrimitiveLong] ||
      info == infoof[PrimitiveFloat] ||
      info == infoof[PrimitiveDouble] ||
      info == infoof[PrimitiveUnit])

  override def equals(other: Any): scala.Boolean =
    other match {
      case other: _Class[_] =>
        info == other.info
      case _ =>
        false
    }

  override def hashCode: Int =
    info.cast[scala.Long].##
}

object _Class {
  private[java] implicit def _class2class[A](cls: _Class[A]): Class[A] =
    cls.asInstanceOf[Class[A]]
  private[java] implicit def class2_class[A](cls: Class[A]): _Class[A] =
    cls.asInstanceOf[_Class[A]]
}

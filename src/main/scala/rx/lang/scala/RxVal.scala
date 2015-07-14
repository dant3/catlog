package rx.lang.scala

trait RxVal[+T] extends Observable[T] with (() ⇒ T) {
   final def get:T = apply()
   def apply():T
 }

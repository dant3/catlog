package com.github.dant3.catlog.util

trait FocusedMutable[MutableT, ImmutableT] {
  def read:ImmutableT
  def modify[O](fn:MutableT ⇒ O):O
}

object FocusedMutable {
  def apply[M,I](mutableData:M, copy:M ⇒ I):FocusedMutable[M,I] = new FocusedMutable[M,I] {
    private var readOnlyCopy = copy(mutableData)

    override def read: I = readOnlyCopy
    override def modify[O](fn: (M) ⇒ O): O = synchronized {
      val output = fn(mutableData)
      readOnlyCopy = copy(mutableData)
      output
    }
  }
}
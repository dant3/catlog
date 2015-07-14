package com.github.dant3.catlog.util

import java.io.{IOException, Closeable}

object IO {
  def closeQuietly(closeable:Closeable):Unit = if (closeable != null) try {
    closeable.close()
  } catch {
    case io:IOException ⇒ /* ignore */
  }

  def closeQuietly(closeable: Closeable*):Unit = for (item ← closeable) closeQuietly(item)
}

package rx.lang.scala

import java.util.concurrent.CopyOnWriteArraySet

import scala.collection.mutable

private[scala] class Rx[T] extends Observer[T] {
  import scala.collection.JavaConversions.asScalaSet

  private val subscribers:mutable.Set[Subscriber[T]] = asScalaSet(new CopyOnWriteArraySet[Subscriber[T]])

  private def foreach(fn:Subscriber[T] ⇒ Any) = {
    subscribers.retain(!_.isUnsubscribed)
    subscribers.foreach(fn)
  }

  override def onNext(value:T) = foreach(_.onNext(value))
  override def onError(error:Throwable) = foreach(_.onError(error))
  override def onCompleted() = foreach(_.onCompleted())

  lazy val observe:Observable[T] = Observable(addSubscriber(_))

  private def addSubscriber(subscriber:Subscriber[T]) = {
    subscribers.add(subscriber)
    onSubscriber(subscriber)
  }

  protected def onSubscriber(subscriber: Subscriber[T]):Any = {}
}
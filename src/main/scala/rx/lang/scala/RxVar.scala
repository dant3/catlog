package rx.lang.scala

trait RxVar[T] extends RxVal[T] {
  final def set(newValue:T):Unit = update(newValue)
  final def :=(newValue:T):RxVar[T] = update(newValue)
  def update(newValue:T):RxVar[T]
}

object RxVar {
  def apply[T](initial:T):RxVar[T] = new Rx[T] with RxVar[T] {
    private var data = initial

    override def update(newValue: T):RxVar[T] = {
      data = newValue
      onNext(newValue)
      this
    }
    override def apply(): T = data

    override private[scala] val asJavaObservable = observe.asJavaObservable
  }
}
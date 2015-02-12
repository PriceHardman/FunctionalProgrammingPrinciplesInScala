trait OldList[T]{
  def isEmpty: Boolean
  def head: T
  def tail: OldList[T]
}

class Cons[T](val head: T, val tail: OldList[T]) extends OldList[T] {
  def isEmpty = false
}

class Nil[T] extends OldList[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}



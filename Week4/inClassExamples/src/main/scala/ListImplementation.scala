import java.util.NoSuchElementException

// Implement an object List initialized with two arguments (a,b), which cause an IntList containing

object ListImplementation extends App {
  println(List(1,2))
}

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
  // head is already implemented by the value parameter head
  // tail is already implemented by the value parameter tail
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

object List {
  def apply[T](elem1: T,elem2: T): List[T] = new Cons(elem1, new Cons(elem2, new Nil))
}



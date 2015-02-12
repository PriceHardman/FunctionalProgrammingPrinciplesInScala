import java.util.NoSuchElementException

//trait IntList
//class Cons(val head: Int, val tail: IntList) extends IntList
//class Nil extends IntList

// This is too restrictive though. If we wanted to define the same sort of list
// class hierarchy, but with type Double instead of Int, we'd have to rewrite everything.
// Instead, we can use type parameters

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

// Functions can also have type parameters, just as classes and traits can:
def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
singleton[Int](1)
singleton[Boolean](true)

// Or letting the compiler infer the type:
singleton(1)
singleton(true)

def nth[T](n: Int, list: List[T]): T = {
  if (list.isEmpty) throw new IndexOutOfBoundsException("n out of bounds")
  else if (n==0) list.head
  else nth(n-1,list.tail)
}

val x = new Cons(1, new Cons(2, new Cons(3, new Nil)))
nth(2,x)







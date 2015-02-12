object FunctionObject extends App {
  // Object-oriented definition of functions:

  // Anonymous function literal
  val f = {x: Int => x * x}
  println(f(7))

  // is equivalent to

  val other_f = new Function1[Int,Int] {
    def apply(x: Int) = x * x
  }
  println(other_f.apply(7))
}

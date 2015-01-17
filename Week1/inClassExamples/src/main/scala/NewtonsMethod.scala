
object NewtonsMethod extends App {
  def sqrt(x: Double): Double = {

    def abs(a: Double): Double =
      if (a >= 0) a else -a

    def isGoodEnough(guess: Double): Boolean =
      abs((guess * guess - x) / x) < 0.001

    def improve(guess: Double): Double =
      (guess + x / guess) / 2

    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess else sqrtIter(improve(guess))

    sqrtIter(1.0)
  }

  println("sqrt(2)=",sqrt(2))
  println("sqrt(4)=",sqrt(4))
  println("sqrt(3.14159)=",sqrt(3.14159))
  println("sqrt(1e60)=",sqrt(1e60))
}

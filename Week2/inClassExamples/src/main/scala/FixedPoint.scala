object FixedPoint extends App {
  import math.abs
  val tolerance = 0.0001

  def isCloseEnough(x: Double, y: Double): Boolean =
    abs((x-y)/x) < tolerance

  def fixedPoint(f: Double => Double)(firstGuess: Double): Double = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess,next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }

  def averageDamp(f: Double => Double)(x: Double): Double = (x + f(x)) /2

  def sqrt(x: Double): Double = {
    fixedPoint(averageDamp(y => x/y))(1.0)
  }

  println("Sqrt(3) = " + sqrt(3))
}

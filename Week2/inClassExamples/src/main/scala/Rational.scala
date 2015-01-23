
object Rational extends App {
  // We want to design a package for doing rational arithmetic.

  val x = new Rational(1,3)
  val y = new Rational(5,7)
  val z = new Rational(3,2)

}

class Rational(x: Int, y: Int) {
  require(y != 0, "Denominator must be non-zero.")
  private def gcd(a: Int, b: Int): Int = if (b==0) a else gcd(b,a%b)

  def this(x: Int) = this(x,1)

  val numer = x
  val denom = y

  def + (that: Rational) =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom
    )

  override def toString = (numer / gcd(numer,denom)) + "/" + (denom / gcd(numer,denom))

  def unary_- : Rational = new Rational(-numer,denom)

  def - (that: Rational): Rational = this + -that

  def < (that: Rational): Boolean = this.numer * that.denom < that.numer * this.denom

  def max(that: Rational): Rational = if(this < that) that else this
}

package nat_implentation

// An implementation of the natural numbers from first principles (Peano numbers).
// Makes no use of primitive numeric types (except for the convenience
// of representing the objects as strings).

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this) // Common to both
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}

object Zero extends Nat {
  def isZero: Boolean = true
  def predecessor: Nat = throw new Error("Zero has no predecessor")
  def + (that: Nat): Nat = that
  def - (that: Nat): Nat =
    if (that.isZero) this
    else throw new Error("Difference is negative.")

  override def toString = "0"
}

class Succ(n: Nat) extends Nat {
  def isZero: Boolean = false
  def predecessor: Nat = n
  def + (that: Nat): Nat =
    if(that.isZero) this else this.successor + that.predecessor
  def - (that: Nat): Nat =
    if(that.isZero) this else this.predecessor - that.predecessor

  override def toString = {
    def iter(i: Int,n: Nat): Int =
      if (n.isZero) i else iter(i+1,n.predecessor)
    iter(0,this).toString
  }
}

object NaturalNumbers extends App {
  val five = Zero.successor.successor.successor.successor.successor
  val seven = five.successor.successor
  println("val five = " + five)
  println("val seven = " + seven)
  println("five + seven = " + (five + seven))
  println("seven - five = " + (seven - five))
}
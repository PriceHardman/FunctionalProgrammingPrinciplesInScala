
object IntSetClasses extends App{
  val t1 = new NonEmpty(5, Empty, Empty) incl 6 incl 3
  val t2 = new NonEmpty(4,Empty,Empty) incl 6 incl 3
  println(s"t1 = $t1,\nt2 = $t2")
  println(s"t1 union t2 = ${t1 union t2}")
  println(s"t1 intersect t2 = ${t1 intersect t2}")
}

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
  def intersect(other: IntSet): IntSet
}
object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def union(other: IntSet): IntSet = other
  def intersect(other: IntSet): IntSet = this
  override def toString = "."
}
class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this
  override def toString = "{" + left + elem + right + "}"

  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem

  def intersect(other: IntSet): IntSet = {
    val l = left intersect other
    val r = right intersect other
    val s = l union r
    if (other contains elem) s incl elem else s
  }
}


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

// How best to implement a method assertAllPos,
// which takes an IntSet, and returns the IntSet itself
// if all of its elements are positive, and throws
// an exception otherwise?

// If passed an instance of Empty, exception thrown
// If passed NonEmpty and all positive, returns NonEmpty
// If passed NonEmpty and not all positive, exception thrown
// While we could define def assertAllPos(x: IntSet): IntSet,
// the logic expressed above would not be captured by such a definition.
// Instead, we can define it in terms of an type bounds using <:
// whereby we explicitly say that any subtype of IntSet can be passed
// to the method, and that type (whatever it may be) will be returned.
def assertAllPos[S <: IntSet](r: S): S = {
  
}

val a: Array[NonEmpty] = Array(new NonEmpty(1,Empty, Empty))
val b: Array[IntSet] = a





package boolean_implementation

// Here we implement a Boolean type from first principles.

abstract class Boolean {
  def ifThenElse[T](t: => T, e: => T): T

  def && (x: => Boolean): Boolean = ifThenElse(x,False)
  def || (x: => Boolean): Boolean = ifThenElse(True,x)
  def unary_! : Boolean           = ifThenElse(False,True)
  def == (x: Boolean): Boolean    = ifThenElse(x,x.unary_!)
  def != (x: Boolean): Boolean    = ifThenElse(x.unary_!,x)
  def < (x: Boolean): Boolean     = ifThenElse(False,x)
}

object True extends Boolean {
  def ifThenElse[T](t: => T, e: => T) = t
  override def toString: String = "True"
}

object False extends Boolean {
  def ifThenElse[T](t: => T, e: => T) = e
  override def toString: String = "False"
}

object BooleanType extends App {
  println("True < False =>"+(True < False))
  println("True < True =>"+(True < True))
  println("False < False =>"+(False < False))
  println("False < True =>"+(False < True))
}
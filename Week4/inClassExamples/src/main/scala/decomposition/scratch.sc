/*
suppose we want to create an interpreter for simple arithmetic expressions.
Lets restrict ourselves for the sake of simplicity to numbers and additions.
Expressions can then be represented as a class hierarchy, with a base trait Expr
and two subclasses Number and Sum.

To treat an expression, its necessary to know the expression's shape and its
components.

This brings us to the following implementation.
*/

trait Expr {
  def isNumber: Boolean
  def isSum: Boolean
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
}

class Number(n: Int) extends Expr {
  def isNumber: Boolean =true
  def isSum: Boolean = false
  def numValue: Int = n
  def leftOp: Expr = throw new Error("Number.leftOp")
  def rightOp: Expr = throw new Error("Number.rightOp")
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  def isNumber: Boolean = false
  def isSum: Boolean = true
  def numValue: Int = throw new Error("Sum.numValue")
  def leftOp: Expr = e1
  def rightOp: Expr = e2
}

class Prod(e1: Expr, e2: Expr) extends Expr {

}

class Var(x: String) extends Expr {

}

// We could write an evaluation function:
def eval(e: Expr): Int = {
  if (e.isNumber) e.numValue
  else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
  else throw new Error("Unknown expression" + e)
}

eval(new Number(10))

eval(new Sum(new Number(10), new Number(20)))

/*
But here's a problem. We've only implemented summation, and already we've defined 15
methods. If we want to add any new operations, the amount of boilerplate code
we need to write quickly balloons out of control.
 */

/*
We are trying to find a general and convenient way to access
objects in an extensible class hierarchy.

Specifically we were trying to implement an arithmetic expression interpreter
with the following hierarchy:
                                    Expr
                          Number  Sum  Product  Var

and we want the ability to do the following:
- eval(): Evaluates the expression
- show(): Gives a string representation of the expression
- simplify(): Simplifies the expression, e.g. simplify(a*c+b*c) = c*(a+b)


The following approaches failed for the following reasons:
- Classification and access methods: quadratic explosion of number of methods
- Type tests and casts: unsafe/low-level
- Object oriented decomposition: does not always work, need to touch all classes
    to add a new method.


Note that the sole purpose of test and accessor functions is to
reverse the construction process:
  - Which subclass was used?
  - What were the arguments to the constructor?
This process is so common that Scala -- like many languages -- automates it.
This is known as pattern matching.

A case class definition is similar to a normal class definition, except
that it is preceded by the modifier "case". It implicitly defines a companion object
containing a factory method for creating instances directly. For example:
*/

trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Var(x: String) extends Expr
case class Prod(e1: Expr, e2: Expr) extends Expr

// The use of "case" allows us to write
Number(1)
// instead of
new Number(1)

/*
Pattern matching is a generalization of the switch statement from C and Java,
allowing the application of the such logic to class hierarchies instead of
values. This is expressed in Scala using the keyword "match":
 */
def eval(e: Expr): Int = e match {
  case Number(n) => n
  case Sum(e1,e2) => eval(e1) + eval(e2)
}

def show(e: Expr): String = e match {
  case Number(n) => n.toString
  case Var(x) => x
  case Sum(e1,e2) => s"${show(e1)}+${show(e2)}"
  case Prod(e1: Sum, e2: Sum) => s"(${show(e1)})*(${show(e2)})"
  case Prod(e1: Sum, e2) => s"(${show(e1)})*${show(e2)}"
  case Prod(e1, e2: Sum) => s"${show(e1)}*(${show(e2)})"
  case Prod(e1, e2) => s"${show(e1)}*${show(e2)}"
}
show(Sum(Prod(Number(2), Var("x")), Var("y")))
show(Prod(Sum(Number(2), Var("x")), Var("y")))





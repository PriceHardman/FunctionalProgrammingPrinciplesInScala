/*
We've seen that some types should be covariant whereas others should not.
Covariant types are those where for Obj, Obj[T] <: Obj[S] if T <: S
Roughly speaking, a type that is mutable should not be covariant,
whereas immutable types can be covariant if some conditions are met.


Suppose C[T] is a parameterized type and A,B are types such that A <: B.
In general, there are three possible relationships between C[A] and C[B]:
  - Covariance:       C[A] <: C[B]
  - Contravariance:   C[A] >: C[B] => C[B] <: C[A]
  - Nonvariance:      no supertype or subtype relationship between C[A] and C[B]


Scala allows for declaration of the variance of a type by annotating the type parameter:
*/
class Class1[+A]{
  // Class1 is covariant
}
class Class2[-A] {
  // Class2 is contravariant
}

class Class3[A] {
  // Class3 is nonvariant.
}

/*
In general, if A2 <: A1 and B1 <: B2
then {A1 => B1} <: {A2 => B2}

which can be expressed with the follwing diagram

A2 => B2
^     V
A1 => B1

We can say that {A1 => B1} is a special case of the {A2 => B2} function.
To see this, suppose we have an argument of type A2.
We can pass this argument to {A2 => B2} (obviously), but we can also
pass it to {A1 => B1} since A2 is a subtype of A1.
Passing A2 to {A2 => B2} will return a result of type B2,
but passing A2 to {A1 => B1} will return a result of type B1, which
qualifies as a B2 since B1 is a subtype of B2.
Thus, {A1 => B1} is a special case of {A2 => B2}, and we can say
that {A1 => B1} <: {A2 => B2}.

So since A2 <: A1, B1 <: B2 => {A1 => B1} <: {A2 => B2},
we can say that functions are contravariant in their argument types,
and covariant in their result types,
since A2 <: A1 => f[A1] <: f[A2]
and B1 <: B2 => f[B1] <: f[B2]
*/

// Thus function1 is defined as
trait Function1[-A,+B] {
  def apply(x: A): B
}

// We can't just make classes co or contravariant as we please. With arrays for
// example:
class Array[+T] {
  def update(x: T)
}

/*
This causes a problem that the Scala compiler catches, warning that
covariant type T appears in contravariant position in type T of value x.

Roughly, the rules the compiler follows are:
- Covariant type parameters can only appear in method results.
- Contravariant type parameters can only appear in method parameters
- Invariant type parameters can appear anywhere.
*/
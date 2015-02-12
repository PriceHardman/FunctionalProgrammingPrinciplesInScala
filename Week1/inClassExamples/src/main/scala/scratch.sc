def factorialHeadRecursive(n: Int): Int =
  if (n==0) 1 else n * factorialHeadRecursive(n-1)

def factorialTailRecursive(n: Int): Int = {
  def factorialIter(x: Int, acc: Int): Int =
    if (x==0) acc else factorialIter(x-1,x*acc)

  factorialIter(n,1)
}

factorialHeadRecursive(11)
factorialTailRecursive(11)
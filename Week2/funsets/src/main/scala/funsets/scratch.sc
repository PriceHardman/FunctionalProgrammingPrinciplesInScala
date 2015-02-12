type Set = Int => Boolean

def contains(s: Set, elem: Int): Boolean = s(elem)

def singletonSet(elem: Int): Set = {x => x == elem}

def gt5(x: Int): Boolean = x > 5
def ls10(x: Int): Boolean = x < 10

def union(s: Set, t: Set): Set = {x => contains(s,x) || contains(t,x)}

def intersect(s: Set, t: Set): Set = {x => contains(s,x) && contains(t,x)}

def diff(s: Set, t: Set): Set = {x => contains(s,x) && !contains(t,x)}

def filter(s: Set, p: Int => Boolean): Set = intersect(s,p)

def forall(s: Set, p: Int => Boolean): Boolean = {
  def iter(a: Int): Boolean = {
    if (a > 1000) true
    else if (contains(s,a) && !p(a)) false
    else iter(a+1)
  }
  iter(-1000)
}

def set_x(x: Int): Boolean = x == 1 || x == 3 || x == 4 || x == 5 || x == 7 || x == 1000
map(set_x,{x => x-1})(999)



def exists(s: Set, p: Int => Boolean): Boolean = !forall(s,{x => !p(x)})

def map(s: Set, f: Int => Int): Set = {x => exists(s,{y => f(y) == x})}






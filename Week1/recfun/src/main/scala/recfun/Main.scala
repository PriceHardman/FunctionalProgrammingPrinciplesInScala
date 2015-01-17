package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    def isEdge: Boolean = (c == 0) || (c == r)

    if (isEdge) 1
    else pascal(c-1,r-1) + pascal(c,r-1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def isLeftParen(c: Char): Boolean = c == '('
    def isRightParen(c: Char): Boolean = c == ')'
    def step(c: Char): Int = if (isLeftParen(c)) -1 else if (isRightParen(c)) 1 else 0
    def updateCount(c: Char, count: Int): Int = count + step(c)
    def isBalanced(count: Int): Boolean = count == 0
    def balanceViolation(c: Char, count: Int): Boolean = isBalanced(count) && isRightParen(c)

    def balanceIter(charList: List[Char],count: Int): Int =
      if (charList.isEmpty)
        count
      else if (balanceViolation(charList.head,count))
        -1 // Break, indicating imbalance
      else balanceIter(charList.tail,updateCount(charList.head,count))

    isBalanced(balanceIter(chars,0))
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if(money == 0) 1
    else if (money < 0) 0
    else if (coins.isEmpty) 0
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)
}

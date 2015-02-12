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


balance("".toList)

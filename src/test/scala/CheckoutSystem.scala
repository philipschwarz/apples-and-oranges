object CheckoutSystem {

  val unrecognisedItem = "unrecognised-item"

  def checkout(items: List[String]): Either[String, Int] = {
    val itemsByType: Map[String, List[String]] = items.groupBy {
      case "apple" => "apple"
      case "orange" => "orange"
      case _ => unrecognisedItem
    }
    val unrecognisedItems = itemsByType.getOrElse(unrecognisedItem, Nil)
    if (unrecognisedItems.isEmpty) {
      val total = itemsByType.map {
        case ("apple", apples) => 60 * (apples.size / 2 + apples.size % 2)
        case ("orange", oranges) => 25 * (oranges.size / 3 * 2 + oranges.size % 3)
      }.foldLeft(0)(_ + _)
      Right(total)
    } else {
      val errorMessage = s"ERROR - the following items were not recognised: ${unrecognisedItems.mkString("'", "','", "'")}."
      Left(errorMessage)
    }
  }

}

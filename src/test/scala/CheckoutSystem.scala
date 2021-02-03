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
      Right(itemsByType.map {
        case ("apple", apples) => 60 * apples.size
        case ("orange", oranges) => 25 * oranges.size
      }.foldLeft(0)(_ + _))
    } else {
      Left(s"ERROR - the following items were not recognised: ${unrecognisedItems.mkString("'", "','", "'")}.")
    }
  }

}

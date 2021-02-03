import ItemType.{Apple, Orange, Unrecognised}

object CheckoutSystem {

  /**
   * Given a list of item names, returns the total price of the items (in pence).
   *
   * Recognised items are "apple" and "orange"
   *
   * If one or more items are not recognised then an error message is returned
   * indicating the item names that were not recognised.
   *
   * @param items
   * @return Either the price of the items (in pence) or an error message
   *         indicating item names that were not recognised.
   */
  def checkout(items: List[String]): Either[String, Int] = {
    val itemsByType: Map[ItemType, List[String]] = groupItemsByType(items)
    val unrecognisedItems: List[String] = getUnrecognisedItems(itemsByType)
    if (unrecognisedItems.isEmpty) {
      Right(computePrice(itemsByType))
    } else {
      Left(s"ERROR - the following items were not recognised: ${unrecognisedItems.mkString("'", "','", "'")}.")
    }
  }

  /**
   * Given a list of item names, e.g. List("apple","orange", "apple"), returns a map from
   * ItemType (e.g. Apple) to the list of items of that type, e.g. List("apple","apple").
   */
  private def groupItemsByType(items: List[String]): Map[ItemType, List[String]] = {
    items.groupBy {
      case "apple" => Apple
      case "orange" => Orange
      case _ => Unrecognised
    }
  }

  /**
   * Given a map from ItemType to a list of items of that type, returns the map's value for
   * ItemType.Unrecognised, i.e. a list of the names of unrecognised items.
   */
  private def getUnrecognisedItems(itemsByType: Map[ItemType, List[String]]): List[String] = {
    itemsByType.getOrElse(Unrecognised, Nil)
  }

  /**
   * Given a map from ItemType to a list of items of that type, returns the price of the
   * all the items in the map.
   */
  private def computePrice(itemsByType: Map[ItemType, List[String]]): Int = {
    computePriceForEachItemType(itemsByType).foldLeft(0)(_ + _)
  }

  /**
   * Given a map from ItemType to a list of items of that type, returns a list
   * containing the price for the items of each type.
   */
  private def computePriceForEachItemType(itemsByType: Map[ItemType, List[String]]): List[Int] = {
    itemsByType.map { case (itemType, items) =>
      val (quantity, price, maybeOffer) = (items.size, itemType.price, itemType.offer)
      maybeOffer.map { offer =>
        price * (quantity / offer.quantity * (offer.quantity - offer.freeQuantity) + quantity % offer.quantity)
      }.getOrElse(price * quantity)
    }.toList
  }
}

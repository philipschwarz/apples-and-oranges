
import ItemType._

object CheckoutSystem {

  def checkout(items: List[String]): Either[String, Int] = {
    val itemsByType: Map[ItemType, List[String]] = groupItemsByType(items)
    val unrecognisedItems: List[String] = getUnrecognisedItems(itemsByType)
    if (unrecognisedItems.isEmpty) {
      Right(computePrice(itemsByType))
    } else {
      Left(s"ERROR - the following items were not recognised: ${unrecognisedItems.mkString("'", "','", "'")}.")
    }
  }

  private def groupItemsByType(items: List[String]): Map[ItemType, List[String]] = {
    items.groupBy {
      case "apple" => Apple
      case "orange" => Orange
      case _ => Unrecognised
    }
  }

  private def getUnrecognisedItems(itemsByType: Map[ItemType, List[String]]): List[String] = {
    itemsByType.getOrElse(Unrecognised, Nil)
  }

  private def computePrice(itemsByType: Map[ItemType, List[String]]) = {
    computePriceForEachItemType(itemsByType).foldLeft(0)(_ + _)
  }

  private def computePriceForEachItemType(itemsByType: Map[ItemType, List[String]]): List[Int] = {
    itemsByType.map { case (itemType, items) =>
      val (quantity, price, maybeOffer) = (items.size, itemType.price, itemType.offer)
      maybeOffer.map { offer =>
        price * (quantity / offer.quantity * (offer.quantity - offer.freeQuantity) + quantity % offer.quantity)
      }.getOrElse(price * quantity)
    }.toList
  }
}

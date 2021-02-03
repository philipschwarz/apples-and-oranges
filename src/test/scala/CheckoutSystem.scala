
import ItemType._

object CheckoutSystem {

  def checkout(items: List[String]): Either[String, Int] = {
    val itemsByType: Map[ItemType, List[String]] = items.groupBy {
      case "apple" => Apple
      case "orange" => Orange
      case _ => Unrecognised
    }
    val unrecognisedItems: List[String] = itemsByType.getOrElse(Unrecognised, Nil)
    if (unrecognisedItems.nonEmpty) {
      Left(s"ERROR - the following items were not recognised: ${unrecognisedItems.mkString("'", "','", "'")}.")
    } else {
      val priceForEachItemType = itemsByType.map { case (itemType, items) =>
        val (quantity, price, maybeOffer) = (items.size, itemType.price, itemType.offer)
          maybeOffer.map{ offer =>
            price * (quantity / offer.quantity * (offer.quantity - offer.freeQuantity) + quantity % offer.quantity)
          }.getOrElse(price * quantity)
      }
      val priceForAllItemTypes = priceForEachItemType.foldLeft(0)(_ + _)
      Right(priceForAllItemTypes)
    }
  }

}

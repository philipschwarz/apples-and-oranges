sealed trait ItemType {
  val name: String
  val price: Int
  val offer: Option[Offer]
}

case class Offer(quantity: Int, freeQuantity: Int)

object ItemType {

  case object Apple extends ItemType {
    override val name: String = "apple"
    override val price: Int = 60
    override val offer: Option[Offer] = Some(Offer(quantity = 2, freeQuantity = 1))
  }

  case object Orange extends ItemType {
    override val name: String = "orange"
    override val price: Int = 25
    override val offer: Option[Offer] = Some(Offer(quantity = 3, freeQuantity = 1))
  }

  case object Unrecognised extends ItemType {
    override val name: String = "unrecognised-item"
    override val price: Int = 0
    override val offer: Option[Offer] = None
  }

}
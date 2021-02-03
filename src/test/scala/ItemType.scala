sealed trait ItemType {
  def name: String
  def price: Int
  def offer: Option[Offer]
}

case class Offer(quantity: Int, freeQuantity: Int)

object ItemType {

  case object Apple extends ItemType {
    override def name: String = "apple"
    override def price: Int = 60
    override def offer: Option[Offer] = Some(Offer(quantity = 2, freeQuantity = 1))
  }

  case object Orange extends ItemType {
    override def name: String = "orange"
    override def price: Int = 25
    override def offer: Option[Offer] = Some(Offer(quantity = 3, freeQuantity = 1))
  }

  case object Unrecognised extends ItemType {
    override def name: String = "unrecognised-item"
    override def price: Int = 0
    override def offer: Option[Offer] = None
  }

}
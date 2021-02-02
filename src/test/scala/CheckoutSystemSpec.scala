import org.specs2.execute.Result
import org.specs2.matcher.Matchers
import org.specs2.mutable.Specification

class CheckoutSystemSpec extends Specification with Matchers {

  "a CheckoutSystem" should {

    "compute the cost of zero items" in zero_items_test
    "compute the cost of one apple" in one_apple_test
    "handle one unrecognised item" in one_unrecognised_item_test
    "handle more than one unrecognised item" in more_than_one_unrecognised_item_test
  }

  def zero_items_test: Result =
    CheckoutSystem.checkout(Nil) must beRight(0)

  def one_apple_test: Result =
    CheckoutSystem.checkout(List("apple")) must beRight(60)

  def one_unrecognised_item_test =
    CheckoutSystem.checkout(List("unrecognised-item")) must beLeft(
      "ERROR - the following items were not recognised: 'unrecognised-item'.")

  def more_than_one_unrecognised_item_test =
    CheckoutSystem.checkout(List("one-unrecognised-item", "another-unrecognised-item")) must beLeft(
      "ERROR - the following items were not recognised: 'one-unrecognised-item','another-unrecognised-item'.")
}
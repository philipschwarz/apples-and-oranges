import org.specs2.execute.Result
import org.specs2.matcher.Matchers
import org.specs2.mutable.Specification

class CheckoutSystemSpec extends Specification with Matchers {

  "a CheckoutSystem" should {

    "compute the cost of zero items" in zero_items_test
    "compute the cost of one apple" in one_apple_test
    "compute the cost of one orange" in one_orange_test
    "compute the cost of one apple and one orange" in one_apple_and_one_orange_test
    "compute the cost of three apples and two oranges" in three_apples_and_two_oranges_test
    "support 'buy one apple get one free' offer" in buy_one_apple_get_one_free
    "support 'three oranges for the price of two' offer" in three_oranges_for_the_price_of_two
    "support multiple offers" in three_oranges_and_two_apples_for_the_price_of_two_oranges_and_one_apple
    "handle one unrecognised item" in one_unrecognised_item_test
    "handle more than one unrecognised item" in more_than_one_unrecognised_item_test
  }

  def zero_items_test: Result =
    CheckoutSystem.checkout(Nil) must beRight(0)

  def one_apple_test: Result =
    CheckoutSystem.checkout(List("apple")) must beRight(60)

  def one_orange_test: Result =
    CheckoutSystem.checkout(List("orange")) must beRight(25)

  def one_apple_and_one_orange_test: Result =
    CheckoutSystem.checkout(List("apple", "orange")) must beRight(85)

  def three_apples_and_two_oranges_test: Result =
    CheckoutSystem.checkout(List("apple", "apple", "orange", "apple", "orange")) must beRight(170)

 def buy_one_apple_get_one_free: Result =
   CheckoutSystem.checkout(List("apple", "apple", "orange")) must beRight(85)

  def three_oranges_for_the_price_of_two: Result =
    CheckoutSystem.checkout(List("orange", "apple", "orange", "orange")) must beRight(110)

  def three_oranges_and_two_apples_for_the_price_of_two_oranges_and_one_apple: Result =
    CheckoutSystem.checkout(List("orange", "apple", "orange", "orange", "apple")) must beRight(110)

  def one_unrecognised_item_test =
    CheckoutSystem.checkout(List("unrecognised-item")) must beLeft(
      "ERROR - the following items were not recognised: 'unrecognised-item'.")

  def more_than_one_unrecognised_item_test =
    CheckoutSystem.checkout(List("one-unrecognised-item", "another-unrecognised-item")) must beLeft(
      "ERROR - the following items were not recognised: 'one-unrecognised-item','another-unrecognised-item'.")
}
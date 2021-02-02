import org.specs2.execute.Result
import org.specs2.matcher.Matchers
import org.specs2.mutable.Specification

class CheckoutSystemSpec extends Specification with Matchers {

  "a CheckoutSystem" should {

    "compute the cost of zero items" in zero_items_test

  }

  def zero_items_test: Result =
    CheckoutSystem.checkout(Nil) must beEqualTo(0)

}
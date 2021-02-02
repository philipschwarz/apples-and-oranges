object CheckoutSystem {

  def checkout(items: List[String]): Either[String, Int] = items match {
    case Nil => Right(0)
    case List("apple") => Right(60)
    case _ => Left(s"ERROR - the following items were not recognised: ${items.mkString("'","','","'")}.")
  }

}

package day7.mongo_practice

class UsersTests extends UnitSpec {
  val customers = new Users

  "We" should "be able to create a new instance of the Users class" in {
    assert(customers.isInstanceOf[Users])
}

  "deleteAll" should "delete all data from collection, do nothing if it's empty" in {
    customers.deleteAll()
    //assert(customers.showUsers())
  }
}

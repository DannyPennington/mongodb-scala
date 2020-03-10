package day7.mongo_practice

import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import day7.mongo_practice.Helpers._

object Main extends App {

  val customers = new Users
  customers.deleteAll()
  customers.addUser("Jason", "jason@fake.com")
  customers.addUser("Jason2", "jason2@fake.com")
  customers.addUser("Jason3", "jason3@fake.com")
  Thread.sleep(1000)
  customers.showUsers()

}
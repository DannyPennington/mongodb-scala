package day7.mongo_practice.models

import org.mongodb.scala.bson.ObjectId

object User {
  def apply(firstname: String, lastname: String, email: String): User = User(new ObjectId(), firstname, lastname, email)
}

case class User(_id: Int, firstname: String, lastname: String, email: String)

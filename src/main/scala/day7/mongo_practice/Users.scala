package day7.mongo_practice

import models.User
import org.mongodb.scala.{Completed, Document, MongoClient, MongoCollection, MongoDatabase, Observable, Observer}
import org.mongodb.scala.model.Filters._
import Helpers._
import myHelper.myHelper._

class Users {
  val client: MongoClient = MongoClient()
  val database: MongoDatabase = client.getDatabase("practice")
  val collection: MongoCollection[User] = database.getCollection("users")

  def addUser(doc:User): Unit = {
    collection.insertOne(doc).subscribe(observeInsert)

  }

  def showUsers(): Unit = {
    collection.find.printResults()
  }

  def editUser(replacementDoc: User): Unit = {
    collection.replaceOne(equal("_id", id), replacementDoc).printHeadResult("Update Result: ")

  }

  def deleteUser(id: Int): Unit = {
    collection.deleteOne(equal("_id", id)).printHeadResult("Delete result: ")
  }

  def deleteMany(ids:List[Int]): Unit = {
    for (i <- ids) {
      collection.deleteOne(equal("_id",i)).printHeadResult("Delete result: ")
    }
  }

  def deleteAll(): Unit = {
    collection.deleteMany(gt("_id",-1)).printHeadResult("Delete result: ")
  }

}

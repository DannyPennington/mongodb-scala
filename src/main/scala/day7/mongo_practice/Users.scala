package day7.mongo_practice

import org.mongodb.scala.{Completed, Document, MongoClient, MongoCollection, MongoDatabase, Observable, Observer}
import org.mongodb.scala.model.Filters._
import Helpers._

class Users {
  val client: MongoClient = MongoClient()
  val database: MongoDatabase = client.getDatabase("practice")
  val collection: MongoCollection[Document] = database.getCollection("users")
  var usercount = 0

  def addUser(name:String, email:String): Unit = {
    val document:Document = Document("_id" -> usercount, "name" -> name, "email" -> email)
    val insertObservable: Observable[Completed] = collection.insertOne(document)
    usercount += 1

    insertObservable.subscribe(new Observer[Completed] {
      override def onNext(result: Completed): Unit = println(s"$result")
      override def onError(e: Throwable): Unit = println(s"Error: $e")
      override def onComplete(): Unit = println("")
    })

  }

  def showUsers(): Unit = {
    collection.find.printResults()
  }

  def editUser(id: Int, new_name: String, new_email: String): Unit = {
    val replacementDoc: Document = Document("name" -> new_name, "email" -> new_email)
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

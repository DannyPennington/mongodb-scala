package day7.mongo_practice

import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import day7.mongo_practice.Helpers._

object Main extends App{
  val client: MongoClient = MongoClient()
  val database: MongoDatabase = client.getDatabase("practice")
  val collection: MongoCollection[Document] = database.getCollection("users")

  def addUser(name:String, email:String, id: Int): Unit = {
    val document:Document = Document("_id" -> id, "name" -> name, "email" -> email)
    val insertObservable: Observable[Completed] = collection.insertOne(document)

    insertObservable.subscribe(new Observer[Completed] {
      override def onNext(result: Completed): Unit = println(s"onNext: $result")
      override def onError(e: Throwable): Unit = println(s"Error: $e")
      override def onComplete(): Unit = println("Finished")
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

  //addUser("Person9", "person9@fakemail.com", 9)
  //Thread.sleep(1000)
  //editUser(6, "Person6","person6@fakemail.com")
  deleteUser(9)
  showUsers()
  Thread.sleep(1000)


}

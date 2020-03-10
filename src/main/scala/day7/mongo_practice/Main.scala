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

  def editUser(person: String): Unit = {
    val replacementDoc: Document = Document("name" -> "New person", "email" -> "newperson@fakemail.com")
    collection.updateOne(equal("name", person), replacementDoc).printHeadResult("Update Result: ")

  }
  //addUser("Person9", "person9@fakemail.com", 9)
  //Thread.sleep(1000)
  //editUser("Person6")
  showUsers()
  Thread.sleep(1000)


}

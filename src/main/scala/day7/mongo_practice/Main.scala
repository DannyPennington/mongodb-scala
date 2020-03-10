package day7.mongo_practice

import org.mongodb.scala._
import org.mongodb.scala.model.Filters
import org.mongodb.scala.result.UpdateResult

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

  def showUsers():Unit = {

    for (i <- 0 until 7) {
      collection.find().collect().subscribe((results: Seq[Document]) => println(s"Name: ${results(i.toInt)("name").asString().getValue}, Email: ${results(i.toInt)("email").asString.getValue} "))
    }
  }


  def editUser(id:Int):Unit = {
    val replacementDoc: Document = Document("_id" -> 9, "name" -> "New person", "email" -> "newperson@fakemail.com")

    collection.replaceOne(Filters.eq("name", "Jason"), replacementDoc).subscribe((updateResult: UpdateResult) => println(updateResult))

  }
  //addUser("Person7", "person7@fakemail.com", 7)
  //Thread.sleep(1000)
  editUser(3)
  showUsers()
  Thread.sleep(1000)


}

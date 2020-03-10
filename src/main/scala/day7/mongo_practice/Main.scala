package day7.mongo_practice

import org.mongodb.scala._

object Main extends App{
  val client: MongoClient = MongoClient()
  val database: MongoDatabase = client.getDatabase("practice")
  val collection: MongoCollection[Document] = database.getCollection("users")

  def addUser(name:String, email:String, id: Int): Unit = {
    val document:Document = Document("_id" -> id, "name" -> name, "email" -> email)
    val insertObservable: Observable[Completed] = collection.insertOne(document)

    insertObservable.subscribe(new Observer[Completed] {
      override def onNext(result: Completed): Unit = println(s"onNext: $result")
      override def onError(e: Throwable): Unit = println(s"onError: $e")
      override def onComplete(): Unit = println("onComplete")
    })
  }

  def showUsers():Unit = {
    for (i <- 0 until 6) {
      collection.find().collect().subscribe((results: Seq[Document]) => println(s"Name: ${results(i.toInt)("name").asString().getValue}, Email: ${results(i.toInt)("email").asString.getValue} "))
    }
  }
  //addUser("Person6", "person6@fakemail.com", 6)

  showUsers()
  Thread.sleep(1000)





}

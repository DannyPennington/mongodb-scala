package day7.messing_around

import org.mongodb.scala._

object Main extends App {

  val mongoClient: MongoClient = MongoClient("mongodb://localhost")
  val database: MongoDatabase = mongoClient.getDatabase("new_db")
  val collection: MongoCollection[Document] = database.getCollection("new")

  //val document: Document = Document("_id" -> 1.0, "name" -> "Jason")
  //val insertObservable: Observable[Completed] = collection.insertOne(document)
//
  //insertObservable.subscribe(new Observer[Completed] {
  //  override def onNext(result: Completed): Unit = println(s"onNext: $result")
  //  override def onError(e: Throwable): Unit = println(s"onError: $e")
  //  override def onComplete(): Unit = println("onComplete")
  //})

  collection.find().collect().subscribe((results: Seq[Document]) => println(s"Found: ${results.size} entries"))
  Thread.sleep(1000)

}

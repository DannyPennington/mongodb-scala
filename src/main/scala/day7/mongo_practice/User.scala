//package day7.mongo_practice
//import org.mongodb.scala._
//
//class User(val customername:String, val email:String) extends Person {
//  def name: String = customername
//  def persontype: String = "User"
//
//  val client: MongoClient = MongoClient()
//  val database: MongoDatabase = client.getDatabase("practice")
//  val collection: MongoCollection[Document] = database.getCollection("users")
//
//  def addUser(): Unit = {
//    val document:Document = Document("_id" -> 0, "name" -> name, "email" -> email)
//    val insertObservable: Observable[Completed] = collection.insertOne(document)
//
//    insertObservable.subscribe(new Observer[Completed] {
//      override def onNext(result: Completed): Unit = println(s"onNext: $result")
//
//      override def onError(e: Throwable): Unit = println(s"onError: $e")
//
//      override def onComplete(): Unit = println("onComplete")
//    })
//  }
//
//}

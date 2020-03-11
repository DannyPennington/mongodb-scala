package day7.mongo_practice.myHelper

import org.mongodb.scala.{Completed, Observer}

object myHelper {

  val observeInsert: Observer[Completed] = new Observer[Completed] {
    override def onNext(result: Completed): Unit = println("Inserted")
    override def onError(e: Throwable): Unit = println(s"Error: $e")
    override def onComplete(): Unit = println("Complete")
  }

}

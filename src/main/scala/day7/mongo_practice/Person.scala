package day7.mongo_practice

abstract class Person {
  def name: String
  def persontype: String
  var ID:Int = 0

  def setID(x: Int) : Unit= {
    ID = x
  }

}

package services

import models.{Client, Product}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by tomek on 3/18/16.
 */
class Repository {
  def all = DB.product.values.map(_._1)
  def clientsPerSlug(slug:String) = DB.product(slug)_2
  def productPerSlug(slug:String) = DB.product(slug)._1
  def clientAsync(slug:String) = Future {
    Thread.sleep(1000)
    clientsPerSlug(slug)
  }
}

object DB {
  val product1 = Product(1, "VW Golf GTI", "Cars", "230000", "bla bla bla", "vwgolfgti")
  val product2 = Product(2, "Toyota Corolla", "Cars", "130000", "khgjdgfjdsg", "toyotacorolla")
  val product3 = Product(3, "Volvo", "Cars", "230000", "bla bla bla", "volvo")
  val product4 = Product(4, "Suzuki", "Cars", "130000", "khgjdgfjdsg", "suzuki")
  val product5 = Product(5, "Mazda", "Cars", "230000", "bla bla bla", "mazda")
  val product6 = Product(6, "Fiat", "Cars", "130000", "khgjdgfjdsg", "fiat")

  val c1 = Client("John Doe", "AA234000XX097987", "2015-12-22", 1234, true)
  val c2 = Client("Mark Glue", "AC234000XX333333", "2014-12-22", 124, false)
  val c3 = Client("Mike Zano", "AA234000XX097987", "2015-12-22", 1235, true)
  val c4 = Client("Yaniv Uzi", "AC234000XX333333", "2014-12-22", 1236, true)

  val product = Map(
    product1.slug -> (product1, List(c1, c2)),
    product2.slug -> (product2, List(c1, c2, c3)),
    product3.slug -> (product3, List(c1, c2)),
    product4.slug -> (product4, List(c1, c2, c3)),
    product5.slug -> (product5, List(c1, c2, c4, c3)),
    product6.slug -> (product6, List(c1, c2, c3, c4))
  )

}
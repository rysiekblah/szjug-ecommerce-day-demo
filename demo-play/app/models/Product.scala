package models

/**
 * Created by tomek on 3/18/16.
 */
case class Product(id:Int, name:String, category:String, price:String, description:String, slug:String)

object Product {
  import play.api.libs.json.Json
  implicit val product = Json.format[Product]
}

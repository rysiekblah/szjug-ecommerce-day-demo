package models

/**
 * Created by tomek on 3/18/16.
 */
case class Client(name:String, account:String, date:String, transaction:Int, vip:Boolean)

object Client {
  import play.api.libs.json.Json
  implicit val client = Json.format[Client]
}

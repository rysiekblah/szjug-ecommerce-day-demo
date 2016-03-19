package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import play.api.libs.json._

import services._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by tomek on 3/18/16.
 */
class ProductController @Inject()(repo:Repository) extends Controller {
  import models._

  def allProducts = Action {
    Ok(Json.toJson(repo.all))
  }

  def product(slug:String) = Action {
    Ok(Json.toJson(repo.productPerSlug(slug)))
  }

  def clients(slug:String) = Action {
    Ok(Json.toJson(repo.clientsPerSlug(slug)))
  }

  def clientsAsync(slug:String) = Action.async {
    repo.clientAsync(slug).map((c:List[Client]) => Ok(Json.toJson(c)))
  }
}

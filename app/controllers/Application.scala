package controllers

import play.api._
import play.api.mvc._
import scala.concurrent._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import jp.applicative.gr.tasks._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def mailMatching = Action.async { request =>
    val dbenv: String = request.getQueryString("dbenv").getOrElse("development")
    val task: String  = request.getQueryString("task").getOrElse("default")

    val futureMailMatching = Future { Task.mailMatching(dbenv, task) }
    futureMailMatching.map(_ => Ok("できたかな？？？"))
  }
}

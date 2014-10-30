package controllers

import play.api._
import play.api.mvc._
import play.api.libs._
import scala.concurrent._
import scala.concurrent.duration._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import jp.applicative.gr.tasks._

object Application extends Controller {

  var busy = false
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def mailMatching = Action { request =>
    val task: String = request.getQueryString("task").getOrElse("default")

    val mailMatchingFuture = Future {
      try {
        Logger.debug(s"before check flg: $busy")
        if(!busy)
          synchronized {
            Logger.debug(s"Thread in the synchronized zone: ${Thread.currentThread().getName()}")
            try {
              busy = true
              Task.mailMatching(task)
            }finally{
              busy = false
            }
          }
      } catch {
        case e:Throwable => Logger.error("MailMatching", e)
      }
    }
    Ok("Done")
  }
}

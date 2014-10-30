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

    val fu = Future {
      Logger.debug(s"before check flg: $busy")
      if(!busy) {
        synchronized {
          Logger.debug(s"mailMatching Thread in the synchronized zone: ${Thread.currentThread().getName()}")
          try {
            busy = true
            Task.mailMatching(task)
          }finally{
            busy = false
          }
        }
      }
    }
    fu.onFailure {
      case t => Logger.error("MailMatching onFailure", t)
    }
    Ok("Done")
  }
  
  def deleteMailMatching = Action { request =>
    val fu = Future {
      synchronized {
        Logger.debug(s"deleteMailMatching Thread in the synchronized zone: ${Thread.currentThread().getName()}")
        Task.deleteMailMatching
      }
    }
    fu.onFailure {
      case t => Logger.error("DeleteMailMatching onFailure", t)
    }
    Ok("Done")
  }
}

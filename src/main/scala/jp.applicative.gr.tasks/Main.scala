package jp.applicative.gr.tasks

import com.twitter.finagle.{ Http, Service }
import com.twitter.util.{ Await, Future }
import java.net.InetSocketAddress
import org.jboss.netty.handler.codec.http._
import org.jboss.netty.buffer.ChannelBuffers.copiedBuffer
import org.jboss.netty.util.CharsetUtil.UTF_8
import scalikejdbc._

object Main {

  def main(args: Array[String]): Unit = {
    Class.forName("com.mysql.jdbc.Driver")
    ConnectionPool.singleton("jdbc:mysql://localhost/grdev", "grdev", "grdev")
    val session = AutoSession
    
    val service = new Service[HttpRequest, HttpResponse] {
      def apply(req: HttpRequest): Future[HttpResponse] = {
        val res = new DefaultHttpResponse(
          req.getProtocolVersion, HttpResponseStatus.OK)
        res.setContent(copiedBuffer("Hello!", UTF_8))
        Future.value(res)
      }
       
    }
    //val server = Http.serve(":8080", service)
    //Await.ready(server)
    
    println((new Matching).list().toString)

  }

}
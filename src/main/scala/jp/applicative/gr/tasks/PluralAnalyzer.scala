package jp.applicative.gr.tasks

import scala.annotation.tailrec

class PluralAnalyzer {
  // standard deviation
  private def sd(s: String): Double = {
    val seq = s.toSeq.map(_.toDouble)
    val len = seq.length
    val avg = seq.sum / len
    return seq.map(x => (x - avg) * (x - avg)).sum / len
  }
  
  private def lines_to_sd(lines: Seq[String]): Seq[Double] = lines.map(sd)
  
  def analyze(body: String): Seq[(Double, String)] = {
    val lines = body.lines.toSeq.map(_.trim).filter(_ != "")
    lines_to_sd(lines).zip(lines)
  }
  
  def split(body: String): Seq[Seq[(Double,String)]] = {
    val ana = analyze(body)
    
    @tailrec
    def f(n: Seq[Seq[(Double,String)]], x: Seq[(Double, String)]): Seq[Seq[(Double,String)]] = {
      val (a,b) = x.span(_._1 > 10)
      if(b.size == 0) {
        n
      } else {
    	f(a+:n, b.tail)
      }
    }
    
    f(Seq(), ana).reverse
  }

}
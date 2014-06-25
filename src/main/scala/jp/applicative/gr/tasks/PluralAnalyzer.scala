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

  private def body_to_sd(body: String): Seq[(Double, String)] = {
    val lines = body.lines.toSeq.map(_.trim).filter(_ != "")
    lines_to_sd(lines).zip(lines)
  }

  def split(body: String): Seq[Seq[(Double, String)]] = {
    val ana = body_to_sd(body)

    @tailrec
    def f(n: Seq[Seq[(Double, String)]], x: Seq[(Double, String)]): Seq[Seq[(Double, String)]] = {
      val (a, b) = x.span(_._1 > 10)
      if (b.size == 0) {
        n
      } else {
        f(a +: n, b.tail)
      }
    }

    f(Seq(), ana).reverse
  }

  def analyze(body: String):Seq[(Int, String, String)] = {
    def sp_initials(x: Seq[Seq[(Double, String)]]) = x.map(seq => seq.map(y => y._2.head).mkString)

    val sp = sp_initials(split(body))
    for {
      a <- sp
      b <- sp
      if a != b
    } yield {(Levenshtein.calc(a,b), a, b)}
  }

}

object Levenshtein {
  def calc(a: String, b: String): Int = {
    val d = Array.ofDim[Int](a.size + 1, b.size + 1)

    // initialization
    for (i <- 0 to a.size) d(i)(0) = i
    for (j <- 0 to b.size) d(0)(j) = j

    for (
      i <- 1 to a.size;
      j <- 1 to b.size
    ) {
      d(i)(j) = List(
        d(i - 1)(j) + 1,
        d(i)(j - 1) + 1,
        d(i - 1)(j - 1) + (if (a(i - 1) == b(j - 1)) 0 else 2)).min
    }

    d(a.size)(b.size)
  }
}
package jp.applicative.gr.tasks

import scala.annotation.tailrec

case class Threshold(delimiter: Int, blockSize: Int, compareLength: Int, analyzeBlocks: Int, distanceLimit: Int, limitClearCount: Int)

case class PluralAnalyzer(t: Threshold) {
  
  private def zenTrim(str: String): String = {
    val f = (x:String) => x.toSeq.dropWhile((y:Char) => "　 \t\n".toSeq.contains(y)).mkString
    val res = f(f(str).reverse).reverse
    res
  }
  
  private type ToDouble = {def toDouble: Double}
  
  // standard deviation
  private def calcSd(s: Seq[Double]): Double = {
    val seq = s
    val len = seq.length
    val avg = seq.sum / len
    return seq.map(x => (x - avg) * (x - avg)).sum / len
  }

  private def body_to_sd(body: String): Seq[(Double, String)] = {
//    body.lines.toSeq.map(x => zenTrim(x)).filter(_ != "").map(y => (sd(y), y))
    body.lines.toSeq.map(x => zenTrim(x)).map(y => (calcSd(y.toSeq.map(_.toDouble)), y))
  }

  private def split(body: String): Seq[Seq[String]] = {
    @tailrec
    def f(n: Seq[Seq[String]], x: Seq[(Double, String)]): Seq[Seq[String]] = {
      val (a, b) = x.span(_._1 > t.delimiter)
      if (b.size == 0) {
        a.map(_._2) +: n
      } else {
        f(a.map(_._2) +: n, b.tail)
      }
    }

    val ana = body_to_sd(body)
    f(Seq(), ana).reverse
  }

  def blocks(x: Seq[Seq[String]]): Seq[(String, String)] = x.map(seq => (seq.map(_.head).mkString, seq.mkString("\n")))

  def analyze(body: String):Seq[(Int, String, String)] = {

    val sp:Seq[String] = blocks(split(body)).map(_._1)
    for {
      (a::as) <- sp.tails.toSeq
      b <- as
      if (a != "" && b != "")
      if (a.length() > t.blockSize && b.length() > t.blockSize)
    } yield {
      (Levenshtein.calc(a.take(t.compareLength),b.take(t.compareLength)), a, b)
    }
  }
  
  def isPlural(body: String):Boolean = {
    val ana = analyze(body)
    if (ana.length < t.analyzeBlocks) return false
    
    val seq = ana.map(x => x._1)
    seq.filter( _ < t.distanceLimit).size >= t.limitClearCount
    
  }

}

private object Levenshtein {
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
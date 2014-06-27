package jp.applicative.gr.tasks

import org.scalatest.FunSpec

class HelloWorldSpec extends FunSpec {
  describe("Adding 1 to 1") {
    it("should equals 2"){
      assert(1+1 == 2)
    }
  }
//  describe("Matching.check") {
//    it("shold equals match strings lists"){
//      assert((new Matching).check(List("aa","bbb","cccc","xx"), List("bbb", "ccc","qqq", "aa")) == List("bbb"))
//      assert((new Matching).check(List("aa","bbb","cccc","xx").sorted, List("bbb", "ccc","qqq", "aa").sorted) == List("aa","bbb"))
//    }
//  }
  describe("Plural") {
    it("xxx"){
        val plu = PluralAnalyzer(Threshold(
            delimiter = 10,
            blockSize = 5,
            compareLength = 10,
            analyzeBlocks = 3,
            distanceLimit = 10,
            limitClearCount = 2))
    	for{
     	  x <- plu.analyze(HelloWorldSpec.body)  
    	}{
    	  //println(x)
    	  //println(a.toInt.toString + " : " + b)
    	}
    	// println(plu.zenTrim("　　　案件番号　　　　　1406-2-2−3816 "))
    }
    
    it("yyy"){
        val plu = PluralAnalyzer(Threshold(
            delimiter = 10,
            blockSize = 5,
            compareLength = 10,
            analyzeBlocks = 3,
            distanceLimit = 10,
            limitClearCount = 2))
        println(plu.isPlural(HelloWorldSpec.body))
      
    }

    //    it("yyy"){
//    	val plu = new PluralAnalyzer
//    	for{
//     	  x <- plu.split(HelloWorldSpec.body)  
//    	}{
//    	  for{
//    		(a, b) <- x  
//    	  } println(a.toInt.toString + " : " + b)
//    	  println()
//    	}
//    	print("あああ".head)
//    }
  }
}

object HelloWorldSpec {
  val body = """
"""
}
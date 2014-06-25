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
    	val plu = new PluralAnalyzer
    	for{
     	  x <- plu.analyze(HelloWorldSpec.body)  
    	}{
    	  println(x)
    	  //println(a.toInt.toString + " : " + b)
    	}
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
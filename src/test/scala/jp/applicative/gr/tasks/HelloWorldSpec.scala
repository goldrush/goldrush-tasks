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
本日10:00時点の案件情報を送信します。

初回のみログイン画面が表示されます。
その後はブラウザを閉じずにメール内のリンクをクリックすることにより再度のログインを省略することができます。

会社名    ：Ａｓｃｅｎｔ　Ｂｕｓｉｎｅｓｓ　Ｃｏｎｓｕｌｔｉｎｇ株式会社
URL　　　 ：http://ascent-biz.com
案件概要  ：金融（証券系）　Oracle DBA
作業形態  ：常駐
作業地域  ：関東 ／東京都
作業場所  ：東京都中央区
ＯＳ      ：
ＤＢ      ：ORACLE
言語      ：
ハードウェア：
ネットワーク：
ツール      ：
フレームワーク：
参入時期  ：2014年7月〜
年齢範囲  ：40歳まで
予算      ：90万円以下
社員区分  ：正社員,契約社員,個人技術者
国籍      ：日本国籍
業種      ：金融 ／証券業務
職務      ：システムエンジニア（SE）,コンサルタント,インフラ（設計）,インフラ（構築）
経験年数  ：11〜20年目
コメント  ：担当：北村contact@acent-biz.com

詳細は以下のリンクをクリックしてください。
リンク　　：http://www2.jiet.or.jp/JSM/JSM_014_00.asp?IssueNo=20140620008

登録者にメールを送信する場合は以下の返信を登録をクリックしてください。
返信を登録：http://www2.jiet.or.jp/JSM/JSM_017_00.asp?IssueNo=20140620008
-------------------------------------------------------------------------

会社名    ：株式会社 システナ
URL　　　 ：http://www.systena.co.jp
案件概要  ：スマートフォン向けアプリ仕様検討
作業形態  ：常駐
作業地域  ：関東 ／東京都
作業場所  ：蒲田
ＯＳ      ：Android,iOS
ＤＢ      ：
言語      ：
ハードウェア：
ネットワーク：
ツール      ：
フレームワーク：
参入時期  ：7月10日
年齢範囲  ：45歳まで
予算      ：80万円以下
社員区分  ：正社員,契約社員,個人技術者
国籍      ：日本国籍
業種      ：
職務      ：
経験年数  ：4〜6年目
コメント  ：

詳細は以下のリンクをクリックしてください。
リンク　　：http://www2.jiet.or.jp/JSM/JSM_014_00.asp?IssueNo=20140620003

登録者にメールを送信する場合は以下の返信を登録をクリックしてください。
返信を登録：http://www2.jiet.or.jp/JSM/JSM_017_00.asp?IssueNo=20140620003
-------------------------------------------------------------------------


リンク先情報を開くときは、Acrobat Readerソフト（無償）
が必要になりますので、
http://www.adobe.co.jp/products/acrobat/readstep.html
からダウンロード後インストールしてご覧下さい。

＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
特定非営利活動法人
日本情報技術取引所（ＪＩＥＴ）
TEL03-6435-1261　FAX03-6435-0868
E-Mail office@jiet.or.jp
http://www2.jiet.or.jp
＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
"""
}
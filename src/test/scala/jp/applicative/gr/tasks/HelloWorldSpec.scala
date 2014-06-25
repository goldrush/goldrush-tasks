package jp.applicative.gr.tasks

import org.scalatest.FunSpec

class HelloWorldSpec extends FunSpec {
  describe("Adding 1 to 1") {
    it("should equals 2"){
      assert(1+1 == 2)
    }
  }
  describe("Matching.check") {
    it("shold equals match strings lists"){
      assert((new Matching).check(List("aa","bbb","cccc","xx"), List("bbb", "ccc","qqq", "aa")) == List("bbb"))
      assert((new Matching).check(List("aa","bbb","cccc","xx").sorted, List("bbb", "ccc","qqq", "aa").sorted) == List("aa","bbb"))
    }
  }
  describe("Plural") {
    it("xxx"){
    	val plu = new PluralAnalyzer
    	for{
     	  (a, b) <- plu.analyze(HelloWorldSpec.body)  
    	}{
    	  //println(a.toInt.toString + " : " + b)
    	}
    }
    
    it("yyy"){
    	val plu = new PluralAnalyzer
    	for{
     	  x <- plu.split(HelloWorldSpec.body)  
    	}{
    	  for{
    		(a, b) <- x  
    	  } println(a.toInt.toString + " : " + b)
    	  println()
    	}
      
    }
  }
}

object HelloWorldSpec {
  val body = """
[PR] MarkeZine News 号外(2014.06.25) http://markezine.jp/

このメールはMarkeZineで会員登録をされた方にお送りしています。
配信の停止は文末をご覧ください。
━━━━━━━━━━━━━━━━━

受け身のコミュニケーションを
SNS活用で能動的なものに変える
「アクティブサポート」の効果とは？

詳しい情報は以下から無料ダウンロード！

▼▽▼ダウンロードはこちら▼▽▼
http://go2.shoeisha.jp/c/add4aeuctWr4u4ab

━━━━━━━━━━━━━━━━━
こんにちは、MarkeZine編集部です。

お客様サポートといえば、多くの場合、
電話などで寄せられる問合せへの対応がメイン業務になっています。

しかし、これは言い換えると
「問い合わせがされないとコミュニケーションは始まらない」
とても受動的な状態ともいえます。

一方、TwitterやFacebookに目を向ければ、問い合わせセンターには届かない
商品やサービスへの疑問や意見が飛び交っています。
彼らの声をきちんとキャッチすることができるか、できないか。
企業にとっては大きな分かれ道となります。

今回紹介する資料では、受け身のサポートに加え、
能動的にソーシャルメディア上でユーザーごとに対話を試みる
「アクティブサポート」開始に伴うプロジェクトを牽引したNTTドコモの担当者が
その経緯と結果を語っています。

これからのサポート業務、顧客コミュニケーションを考える上で
参考になる資料ですので、ぜひご覧ください！

▼▽▼ 無料ダウンロードはこちらから ▼▽▼
http://go2.shoeisha.jp/c/add4aeuctWr4u4ab

-----------------------------------
★資料概要
-----------------------------------
■タイトル
【事例資料】NTTドコモが語る
「アクティブサポート導入のポイントと効果」
能動的なサポートで顧客満足度を向上するには？

■概要
本資料は、アクティブサポートを導入した、
NTTドコモの事例を紹介する資料です。
導入前の課題や結果が、わかりやすくまとめられています。
従来、ドコモのサポート業務は、来店や問合せがスタートでした。
ソーシャルメディアを使い能動的なサポートを行うことで、
NTTドコモは顧客との関係はどのように変化したのか。
これからの顧客コミュニケーションについて、お考えのかたには、
非常に参考になる内容です。ぜひ、この機会にご覧ください！

■トピック
課題：多様化するモバイル環境のなかで新たなお客様サポートを実施
経緯：最新のテクノロジーを活用した幅広い運営ノウハウの提供が決め手
効果：現場と一体になってCS向上に手ごたえ
今後：誠実さと人間味ある対応で新たなファン開拓へ

▼▽▼ 無料ダウンロードはこちらから ▼▽▼
http://go2.shoeisha.jp/c/add4aeuctWr4u4ab

━━━━━━━━━━━━━━━━━
配信停止の方法
━━━━━━━━━━━━━━━━━
1）SEメンバーシップの会員情報編集画面へジャンプ
http://go2.shoeisha.jp/c/add4aeuctWr4u4ac

2）配信停止したいメールニュースの欄にメールアドレスを入力後、
[解除]ボタンをクリック

・ニュースの内容は予告なしに変更される場合があります。
・記事中の会社名、製品名は、弊社および各社の登録商標、商標です。
・お問い合わせについては、 support@markezine.jp へご連絡ください。
─────────────────
発行:株式会社翔泳社 MarkeZine編集部
(c)2006-2014 SHOEISHA. All rights reserved.
"""
}
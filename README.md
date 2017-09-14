# ANTLR_Hands-on
# ANTLRとは
ANTLRは，パーサジェネレータの一種です．
パーサジェネレータは，文字通り，パーサを生成するためのものです．
パーサは，日本語では構文解析器のことです．
つまり，ANTLRは構文解析器を生成するもののことです．

代表的なものとして，yacc,bison,JavaCCなどがあります．

一般に，構文解析をするためのアルゴリズムとして下向き構文解析や上向き構文解析があります．
それらのアルゴリズムを利用して構文を解析するプログラムを，手で書くのは大変な労力がかかるため，
yaccなどが作られました．

最新のANLTRのバージョンは４です．

ANTLRを利用することにより，ANTLRの文法に従って文法ファイルを記述すると，パーサのプログラムを自動生成できます．
パーサのプログラムは，Javaや，C，C#，Pythonといった言語のパーサを作ることができます．


パーサを利用することにより，構文が決まっているものを，構文解析して様々情報を取得できます．

例をあげると，
XMLやJSONやCSVなどから必要な情報を取得，
統合開発環境で，ソースコードをハイライトする機能を構築（パーサにより，ハイライトする箇所を選択），
といったことができます．

XMLやJSONやCSVに利用する場合は，多くの言語でパーサのAPIがあるので，ANLTRを利用してパーサを作る必要はまずありません．

しかし，ソースコードの静的解析を行いたい場合などは，ANTLRを利用して構文解析する必要があります．
公式リファレンスは，こちらです．
http://www.antlr.org/　


# ANTLRのインストール

ANTLRのインストールは，Jarファイルをダウンロードしてきて CLASSPATH，aliasの設定を行うことによって動きます．

Windowsはよくわからないので，各自調べてください．一応公式リファレンスに記載されていたものを引用して持ってきます．

```
Download http://antlr.org/download/antlr-4.7-complete.jar.
Add antlr4-complete.jar to CLASSPATH, either:
Permanently: Using System Properties dialog > Environment variables > Create or append to CLASSPATH variable
Temporarily, at command line:
SET CLASSPATH=.;C:\Javalib\antlr4-complete.jar;%CLASSPATH%
Create batch commands for ANTLR Tool, TestRig in dir in PATH
 antlr4.bat: java org.antlr.v4.Tool %*
 grun.bat:   java org.antlr.v4.gui.TestRig %*

```

Linux
```
$ cd /usr/local/lib
$ sudo wget http://www.antlr.org/download/antlr-4.7-complete.jar
$ cd 
$ echo export CLASSPATH=".:/usr/local/lib/antlr-4.7-complete.jar:\$CLASSPATH" >> ~/.bashrc 
$ echo alias antlr4='java -jar /usr/local/lib/antlr-4.7-complete.jar' >> ~/.bashrc 
$ echo alias grun='java org.antlr.v4.gui.TestRig' >> ~/.bashrc 
$ source ~/.bashrc
```

Mac
```
$ cd /usr/local/lib
$ sudo curl -O http://www.antlr.org/download/antlr-4.7-complete.jar
$ cd 
$ echo export CLASSPATH=".:/usr/local/lib/antlr-4.7-complete.jar:\$CLASSPATH" >> ~/.bashrc 
$ echo alias antlr4='java -jar /usr/local/lib/antlr-4.7-complete.jar' >> ~/.bashrc 
$ echo alias grun='java org.antlr.v4.gui.TestRig' >> ~/.bashrc 
$ source ~/.bashrc
```

bashrcに書き込まないと，再起動時に設定がなくなってしまうので，bashrcに書き込んでくさい．
bashrcへの書き込みは，echoコマンドとリダイレクト入力（>>)を利用します．
テキストエディタを利用して書き込んでも構いません．
sourceコマンドを利用して，書き込んだ設定を反映させるのを忘れないでください．

コマンドが動作するかの確認として下記コマンドを入力し，下記のような結果が出ればインストールに成功しています．
```
$ antlr4
ANTLR Parser Generator  Version 4.7
 -o ___              specify output directory where all output is generated
 -lib ___            specify location of grammars, tokens files
 -atn                generate rule augmented transition network diagrams
 -encoding ___       specify grammar file encoding; e.g., euc-jp
 -message-format ___ specify output style for messages in antlr, gnu, vs2005
 -long-messages      show exception details when available for errors and warnings
 -listener           generate parse tree listener (default)
 -no-listener        don't generate parse tree listener
 -visitor            generate parse tree visitor
 -no-visitor         don't generate parse tree visitor (default)
 -package ___        specify a package/namespace for the generated code
 -depend             generate file dependencies
 -D<option>=value    set/override a grammar-level option
 -Werror             treat warnings as errors
 -XdbgST             launch StringTemplate visualizer on generated code
 -XdbgSTWait         wait for STViz to close before continuing
 -Xforce-atn         use the ATN simulator for all predictions
 -Xlog               dump lots of logging info to antlr-timestamp.log
 
 $ grun                                                                                                                                                               java org.antlr.v4.gui.TestRig GrammarName startRuleName
  [-tokens] [-tree] [-gui] [-ps file.ps] [-encoding encodingname]
  [-trace] [-diagnostics] [-SLL]
  [input-filename(s)]
Use startRuleName='tokens' if GrammarName is a lexer grammar.
Omitting input-filename makes rig read from stdin.
 
```

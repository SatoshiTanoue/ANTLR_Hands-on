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

最新のANLTRのバージョンは４(2017/9/13)です．

ANTLRの文法に従って文法ファイルを記述すると，パーサのプログラムを自動生成できます．
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

Windows
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
$ echo "alias antlr4='java -jar /usr/local/lib/antlr-4.7-complete.jar'" >> ~/.bashrc 
$ echo "alias grun='java org.antlr.v4.gui.TestRig'" >> ~/.bashrc 
$ source ~/.bashrc
```

Mac
```
$ cd /usr/local/lib
$ sudo curl -O http://www.antlr.org/download/antlr-4.7-complete.jar
$ cd 
$ echo export CLASSPATH=".:/usr/local/lib/antlr-4.7-complete.jar:\$CLASSPATH" >> ~/.bashrc 
$ echo "alias antlr4='java -jar /usr/local/lib/antlr-4.7-complete.jar'" >> ~/.bashrc 
$ echo "alias grun='java org.antlr.v4.gui.TestRig'" >> ~/.bashrc 
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

# Hello World
 
 では早速，Hello Worldを動かしてみましょう．
 

 ```
$ git clone https://github.com/SatoshiTanoue/ANTLR_Hands-on.git #ダウンロード
$ cd ANTLR_Hands-on/Hello　#Helloのあるディレクトリに移動．
$ antlr4 Hello.g4 #Helloという文法ファイルからパーサ，レキサーを生成．
$ ls #生成されたファイルを確認 
$ javac *.java 
$ grun Hello r -gui 
  Hello Satoshi 
  #ctl+d  で終了
 ```

GUIで木構造が表示されたでしょう．grunは，antlrのテストのために作られたコマンドです．
grunを使うことにより，シェル上で，実行結果を確認できます．

動かしてみたところで，Hello.g4の中身を見てみましょう．

```Hello.g4
1: grammar Hello;
2: r : 'hello' ID ;
3: ID : [a-z]+ ;
4: WS : [ \t\r\n]+ -> skip ;
```

字句（終端文字）を定義する場合は，大文字を使います．字句を英語でトークン（Token）と言います．
Hello.g4では，字句はID，WSです．
正規表現を利用することができ，3行目のIDは，aからzまでの1文字以上の繰り返しの文字にマッチします．
4行目のWSは，タブ文字や改行文字の1文字以上の繰り返しにマッチします．
タブ文字や改行文字は無視したいので， スキップを利用して無視しています．
構文（非終端文字）を定義する場合は，小文字を使います．構文を英語でシンタックス（Syntax)と言います．
2行目のrという構文は，'hello'とIDという文字からなるという意味になります．

# HelloをJavaのソースコードから呼び出す

ANLTRを利用して，パーサを生成し，テスト用のgrunコマンドを利用して動かすことができました．
次は，生成されたパーサのプログラムをJavaから呼び出してみます．

次のJavaファイルを作成してください．
ファイル名はMain.javaにしてください．

```Main.java
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.Trees;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(System.in)
	// Lexerを作成．
        HelloLexer lexer = new HelloLexer(input);
	//トークンを取得
        CommonTokenStream tokens = new CommonTokenStream(lexer);
	//パーサを作成 
        HelloParser parser = new HelloParser(tokens);
	//パースする．
        ParseTree tree = parser.r();   
        //GUIで表示 
        Trees.inspect(tree,parser);
    }
}
```


```
$ javac -Xlint:deprecation Main.java
$ java Main
Hello World

# ctrl + d or ctrl + z
```

# Javaのソースコードからフィールド名を取得する

Javaのソースコードから，フィールド名を取得しみましょう
ANTLRは，字句解析を行うLexer，構文解析解を行うParserを自動生成します．
この自動生成されたLexer，Parserを使用することで，構文規則木を作ることができます．

この構文木から情報を取得するには，Listenerを使う必要があります．
Listenerを利用するには，AntlrがBaseListnerを生成するので，
そのクラスを継承して新しい子クラスを作る必要があります．

JavaBaseListenr.javaというファイルがあります．このクラスを継承して新しいクラスを作ります．
このJavaの部分は，文法ファイルによって名前が変わります．
今回は，Java.g4という名前の文法ファイルから生成しているので，Javaとなっています

Javaディレクトリに移動してください．
```
cd ../Java
```
ファイル名はMain.javaにして下記ファイルを作成してください．

```Main.java
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main{
   public static void main(String[] args) throws Exception {
      String filePath="examples/Item.java";
      start(filePath);
}
   public static void start(String filePath){

      try {
      　　//ファイル開いて，読み込み
         InputStream is = new FileInputStream(filePath);
         ANTLRInputStream input = new ANTLRInputStream(is);
         // Lexerの作成
         JavaLexer lexer= new JavaLexer(input);
         // Tokenの取得
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         //Parserの作成
         JavaParser parser = new JavaParser(tokens);
         ParseTree tree = parser.compilationUnit();    

	　　//Listenerを用いて探索
         ParseTreeWalker walker = new ParseTreeWalker();
         ExtractListener extractor = new ExtractListener(parser);
         walker.walk(extractor,tree);

         Trees.inspect(tree,parser);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }
}
```
次に，JavaBaseListenrを継承したExtractListenerを作ります．
このExtractListenrを利用して，構文木から情報を取得します．
構文木から情報を取得する場合には，文法ファイルを見る必要があります．
Java.g4では，下記部分が変数宣言の箇所です．

```Java.g4
variableDeclarators
    :   variableDeclarator (',' variableDeclarator)*
    ;

variableDeclarator
    :   variableDeclaratorId ('=' variableInitializer)?
    ;

variableDeclaratorId
    :   Identifier ('[' ']')*
    ;

```

これを利用して，必要なBaseListenerのメソッドをオーバーライドします．
enterはある構文に入ったときに動き，exitはある構文から出たときに動きます．

ファイル名は，ExtractListener.javaにしてください．
``` ExtractListenr.java

import org.antlr.v4.runtime.TokenStream;

/**
 * Created by satopi on 2017/09/19.
 */
public class ExtractListener extends JavaBaseListener{
    JavaParser parser;
    public ExtractListener(JavaParser parser){
        this.parser = parser;
    }
    @Override
    public void enterVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx){
        System.out.println("呼び出された際のFiled名:"+ctx.Identifier());
    }

    @Override
    public void enterVariableDeclarator(JavaParser.VariableDeclaratorContext ctx){
	System.out.println("変数宣言時のFiled名"+ctx.variableDeclaratorId().getText());
    }
    /*@Override
    public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx){
        System.out.println("フィールド宣言時のFiled名:"+ctx.variableDeclarators().getText());
    }
    @Override
    public void enterMemberDeclaration(JavaParser.MemberDeclarationContext ctx){
        if(ctx.methodDeclaration() != null) {
            System.out.println("メソット宣言時のメソッド名:" + ctx.methodDeclaration().Identifier());
        }
    }
    */
}
```
コンパイルして実行

```
$ antlr4 java.g4
$ javac Java*.java
$ javac -Xlint:deprecation Main.java
$ java Main
```




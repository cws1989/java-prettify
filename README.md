# Java Prettify #

> Java Prettify is discontinued.

## Menu ##
  * [ThemesDemo](https://github.com/cws1989/java-prettify/blob/wiki/ThemesDemo.md)
  * [ConfigurationsDemo](https://github.com/cws1989/java-prettify/blob/wiki/ConfigurationsDemo.md)
  * [CreateNewLanguage](https://github.com/cws1989/java-prettify/blob/wiki/CreateNewLanguage.md)
  * [CreateNewTheme](https://github.com/cws1989/java-prettify/blob/wiki/CreateNewTheme.md)
  * [UseTheParserAlone](https://github.com/cws1989/java-prettify/blob/wiki/UseTheParserAlone.md)

## Overview ##
This library is a java port of [Google Prettify](https://github.com/google/code-prettify), the current version ported is 1-Jun-2011. The copyright holder of the
Google Prettify is Mike Samuel (mikesamuel@gmail.com). It is licensed under the [Apache license Version 2](http://www.opensource.org/licenses/Apache-2.0). This port is written by Chan Wai Shing (cws1989@gmail.com) distributed under
[Apache license Version 2](http://www.opensource.org/licenses/Apache-2.0).

  * If you need an editor more than a highlighter, please find [jsyntaxpane](https://github.com/nordfalk/jsyntaxpane).
  * I port it for [Language Files Tool](https://github.com/cws1989/language-files-tool).

## Alternatives ##
[Java SyntaxHighlighter](https://github.com/cws1989/java-syntax-highlighter/)

## Requirement ##
Java SE 6 or up

## Language Supported ##
The comments in [prettify.parser.Prettify](http://cws1989.github.io/java-prettify/prettify/parser/Prettify.html) are authoritative but the lexer should work on a number of languages including C and friends, Java, Python, Bash, SQL, HTML, XML, CSS, Javascript, and Makefiles. It works passably on Ruby, PHP, VB, and Awk and a decent subset of Perl and Ruby, but, because of commenting conventions, doesn't work on Smalltalk, or CAML-like languages.

LISPy languages are supported via an extension: [prettify.lang.LangLisp](http://cws1989.github.io/java-prettify/prettify/lang/LangLisp.html).

And similarly for Clojure, CSS, Go, Haskell, Lua, OCAML, SML, F#, Nemerle, Protocol Buffers, Scala, SQL, TeX, LaTeX, VHDL, Visual Basic, WikiText, XQuery, and YAML.

If you'd like to add an extension for your favorite language, please look at [prettify.lang.LangLisp](https://github.com/cws1989/java-prettify/tree/master/src/prettify/lang/LangLisp.java) and file an [issue](https://github.com/google/code-prettify/issues) including your language extension, and a testcase.

Adapted from: https://github.com/google/code-prettify/blob/master/README.md

## Themes ##
Default, Desert, Sons of Obsidian, Sunburst

[Click here to visit the gallery.](https://github.com/cws1989/java-prettify/blob/wiki/ThemesDemo.md)

## Configurations ##
  * Allows you to change the first (starting) line number.
  * Allows you to turn gutter with line numbers on and off.
  * Allows you to highlight one or more lines to focus user's attention.

[Click here for a demo.](https://github.com/cws1989/java-prettify/blob/wiki/ConfigurationsDemo.md)

## Example ##
> **Note that this highlighter extends Swing component, so all operations are better be executed inside [Swing dispatching thread](http://en.wikipedia.org/wiki/Event_dispatching_thread).**

```java
import java.io.*;
import java.util.Arrays;
import java.util.logging.*;
import javax.swing.*;
import prettify.PrettifyParser;
import prettify.theme.ThemeDefault;
import syntaxhighlight.*;

public class Example {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        // the Prettify parser
        Parser parser = new PrettifyParser();

        // initialize the highlighter and use Default theme
        SyntaxHighlighter highlighter = new SyntaxHighlighter(parser, new ThemeDefault());
        // set the line number count from 10 instead of 1
        highlighter.setFirstLine(10);
        // set to highlight line 13, 27, 28, 38, 42, 43 and 53
        highlighter.setHighlightedLineList(Arrays.asList(13, 27, 28, 38, 42, 43, 53));
        try {
          highlighter.setContent(new File("test.html"));
        } catch (IOException ex) {
          Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(highlighter);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
      }
    });
  }
}
```

## Sample Screenshot ##
![ThemeDesert](https://raw.githubusercontent.com/cws1989/java-prettify/wiki/ThemesDemo/ThemeDesert.png)

## Support & Discussion ##
[Support & Discussion Group](http://groups.google.com/group/java-prettify)

## Known Issues ##
  * Perl formatting is really crappy. Partly because Perl is [hard](http://www.perlmonks.org/?node_id=663393) to parse.

Adapted from: https://github.com/google/code-prettify/blob/master/CHANGES.md

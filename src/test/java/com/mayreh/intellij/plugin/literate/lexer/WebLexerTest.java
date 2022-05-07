package com.mayreh.intellij.plugin.literate.lexer;

import com.intellij.lexer.Lexer;
import com.mayreh.intellij.plugin.literate.LexerTestCaseBase;

public class WebLexerTest extends LexerTestCaseBase {
    public WebLexerTest() {
        super("literate/lexer/fixtures", "web");
    }

    @Override
    protected Lexer createLexer() {
        return new WebLexer();
    }

    public void test_minimal() {
        doTest();
    }
}

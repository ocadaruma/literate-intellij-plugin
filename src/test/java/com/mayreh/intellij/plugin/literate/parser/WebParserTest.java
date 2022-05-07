package com.mayreh.intellij.plugin.literate.parser;

import com.intellij.lang.ParserDefinition;
import com.mayreh.intellij.plugin.literate.ParserTestCaseBase;
import com.mayreh.intellij.plugin.literate.WebParserDefinition;

public class WebParserTest extends ParserTestCaseBase {
    public WebParserTest() {
        super("literate/parser/fixtures", "web", new WebParserDefinition());
    }

    public void test_minimal() {
        doTest();
    }
}

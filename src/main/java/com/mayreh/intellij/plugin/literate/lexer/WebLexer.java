package com.mayreh.intellij.plugin.literate.lexer;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.LayeredLexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;
import com.mayreh.intellij.plugin.literate.psi.WebElementTypes;

public class WebLexer extends LayeredLexer {
    public WebLexer() {
        super(new MergingLexerAdapter(
                new FlexAdapter(new _WebLexer(null)),
                TokenSet.create(
                        WebElementTypes.COMMENT,
                        WebElementTypes.TEX_PART,
                        WebElementTypes.TEX_FRAGMENT,
                        WebElementTypes.DEFINITION_PART,
                        WebElementTypes.PASCAL_PART,
                        WebElementTypes.PASCAL_FRAGMENT,
                        WebElementTypes.INDEX_HINT_TEXT)));
    }
}

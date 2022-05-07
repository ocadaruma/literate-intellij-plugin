package com.mayreh.intellij.plugin.literate;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

import org.jetbrains.annotations.NotNull;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.mayreh.intellij.plugin.literate.lexer.WebLexer;
import com.mayreh.intellij.plugin.literate.psi.WebElementTypes;

public class WebSyntaxHighlighter extends SyntaxHighlighterBase {
    private static TextAttributesKey createAttrKey(String name, TextAttributesKey key) {
        return createTextAttributesKey("com.mayreh.web." + name, key);
    }

    public static final TextAttributesKey COMMENT = createAttrKey(
            "COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    public static final TextAttributesKey KEYWORD = createAttrKey(
            "COMMENT", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey TEX = createAttrKey(
            "TEX", DefaultLanguageHighlighterColors.DOC_COMMENT);
    public static final TextAttributesKey DEFINITION = createAttrKey(
            "DEFINITION", DefaultLanguageHighlighterColors.STATIC_FIELD);
    public static final TextAttributesKey PASCAL = createAttrKey(
            "PASCAL", DefaultLanguageHighlighterColors.IDENTIFIER);

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new WebLexer();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(WebElementTypes.COMMENT)) {
            return pack(COMMENT);
        }
        if (tokenType.equals(WebElementTypes.TEX_PART)) {
            return pack(TEX);
        }
        if (tokenType.equals(WebElementTypes.DEFINITION_PART)) {
            return pack(DEFINITION);
        }
        if (tokenType.equals(WebElementTypes.PASCAL_PART)) {
            return pack(PASCAL);
        }
        if (tokenType.equals(WebElementTypes.MODULE_BEGIN) ||
            tokenType.equals(WebElementTypes.DEFINITION_PART_BEGIN) ||
            tokenType.equals(WebElementTypes.PASCAL_PART_BEGIN)) {
            return pack(KEYWORD);
        }
        return TextAttributesKey.EMPTY_ARRAY;
    }
}

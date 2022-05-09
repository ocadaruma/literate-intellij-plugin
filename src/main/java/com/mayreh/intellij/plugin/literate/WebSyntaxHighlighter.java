package com.mayreh.intellij.plugin.literate;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

import org.jetbrains.annotations.NotNull;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.mayreh.intellij.plugin.literate.lexer.TokenSets;
import com.mayreh.intellij.plugin.literate.lexer.WebLexer;
import com.mayreh.intellij.plugin.literate.psi.WebElementTypes;

public class WebSyntaxHighlighter extends SyntaxHighlighterBase {
    private static TextAttributesKey createAttrKey(String name, TextAttributesKey key) {
        return createTextAttributesKey("com.mayreh.web." + name, key);
    }

    public static final TextAttributesKey COMMENT = createAttrKey(
            "COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    public static final TextAttributesKey KEYWORD = createAttrKey(
            "KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey TEX = createAttrKey(
            "TEX", DefaultLanguageHighlighterColors.DOC_COMMENT);
    public static final TextAttributesKey DEFINITION = createAttrKey(
            "DEFINITION", DefaultLanguageHighlighterColors.STATIC_FIELD);
    public static final TextAttributesKey PASCAL = createAttrKey(
            "PASCAL", DefaultLanguageHighlighterColors.IDENTIFIER);

    public static final TextAttributesKey STRING = createAttrKey(
            "STRING", DefaultLanguageHighlighterColors.STRING);

    public static final TextAttributesKey NUMBER = createAttrKey(
            "Number", DefaultLanguageHighlighterColors.NUMBER);

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
        if (tokenType.equals(WebElementTypes.PASCAL_STRING) ||
            tokenType.equals(WebElementTypes.PREPROCESSED_STRING)) {
            return pack(STRING);
        }
        if (tokenType.equals(WebElementTypes.OCTAL_CONSTANT) ||
            tokenType.equals(WebElementTypes.HEX_CONSTANT)) {
            return pack(NUMBER);
        }
        if (TokenSets.CONTROL_CODES.contains(tokenType) ||
            TokenSets.PASCAL_RESERVED_WORDS.contains(tokenType)) {
            return pack(KEYWORD);
        }
        return TextAttributesKey.EMPTY_ARRAY;
    }
}

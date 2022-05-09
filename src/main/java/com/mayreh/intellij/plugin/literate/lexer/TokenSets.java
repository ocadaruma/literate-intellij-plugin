package com.mayreh.intellij.plugin.literate.lexer;

import com.intellij.psi.tree.TokenSet;
import com.mayreh.intellij.plugin.literate.psi.WebElementTypes;

public interface TokenSets {
    TokenSet CONTROL_CODES = TokenSet.create(
            WebElementTypes.CONTROL_CODE_UNSTARRED_MODULE,
            WebElementTypes.CONTROL_CODE_STARRED_MODULE,
            WebElementTypes.CONTROL_CODE_MACRO_DEFINITION,
            WebElementTypes.CONTROL_CODE_FORMAT_DEFINITION,
            WebElementTypes.CONTROL_CODE_UNNAMED_PASCAL,
            WebElementTypes.CONTROL_CODE_NAMED_PASCAL,
            WebElementTypes.CONTROL_CODE_AT_GT,
            WebElementTypes.CONTROL_CODE_PASCAL_DEFINITION,
            WebElementTypes.CONTROL_CODE_AT_CARET,
            WebElementTypes.CONTROL_CODE_AT_DOT,
            WebElementTypes.CONTROL_CODE_AT_COLON,
            WebElementTypes.CONTROL_CODE_AT_T,
            WebElementTypes.CONTROL_CODE_AT_EQ
    );

    TokenSet PASCAL_RESERVED_WORDS = TokenSet.create(
            WebElementTypes.PASCAL_KEYWORD_AND,
            WebElementTypes.PASCAL_KEYWORD_ARRAY,
            WebElementTypes.PASCAL_KEYWORD_BEGIN,
            WebElementTypes.PASCAL_KEYWORD_CASE,
            WebElementTypes.PASCAL_KEYWORD_CONST,
            WebElementTypes.PASCAL_KEYWORD_DIV,
            WebElementTypes.PASCAL_KEYWORD_DO,
            WebElementTypes.PASCAL_KEYWORD_DOWNTO,
            WebElementTypes.PASCAL_KEYWORD_ELSE,
            WebElementTypes.PASCAL_KEYWORD_END,
            WebElementTypes.PASCAL_KEYWORD_FILE,
            WebElementTypes.PASCAL_KEYWORD_FOR,
            WebElementTypes.PASCAL_KEYWORD_FUNCTION,
            WebElementTypes.PASCAL_KEYWORD_GOTO,
            WebElementTypes.PASCAL_KEYWORD_IF,
            WebElementTypes.PASCAL_KEYWORD_IN,
            WebElementTypes.PASCAL_KEYWORD_LABEL,
            WebElementTypes.PASCAL_KEYWORD_MOD,
            WebElementTypes.PASCAL_KEYWORD_NIL,
            WebElementTypes.PASCAL_KEYWORD_NOT,
            WebElementTypes.PASCAL_KEYWORD_OF,
            WebElementTypes.PASCAL_KEYWORD_OR,
            WebElementTypes.PASCAL_KEYWORD_PACKED,
            WebElementTypes.PASCAL_KEYWORD_PROCEDURE,
            WebElementTypes.PASCAL_KEYWORD_PROGRAM,
            WebElementTypes.PASCAL_KEYWORD_RECORD,
            WebElementTypes.PASCAL_KEYWORD_REPEAT,
            WebElementTypes.PASCAL_KEYWORD_SET,
            WebElementTypes.PASCAL_KEYWORD_THEN,
            WebElementTypes.PASCAL_KEYWORD_TO,
            WebElementTypes.PASCAL_KEYWORD_TYPE,
            WebElementTypes.PASCAL_KEYWORD_UNTIL,
            WebElementTypes.PASCAL_KEYWORD_VAR,
            WebElementTypes.PASCAL_KEYWORD_WHILE,
            WebElementTypes.PASCAL_KEYWORD_WITH
    );
}

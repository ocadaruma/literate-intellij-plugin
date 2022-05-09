package com.mayreh.intellij.plugin.literate.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.mayreh.intellij.plugin.literate.psi.WebElementTypes;

%%

%unicode
%public
%class _WebLexer
%implements FlexLexer
%function advance
%type IElementType

%state ZZ_TEX_PART
%state ZZ_DEFINITION_PART
%state ZZ_PASCAL_PART
%state ZZ_PASCAL_NAME_TEX_FRAGMENT
%state ZZ_INDEX_HINT_IN_TEX
%state ZZ_INDEX_HINT_IN_PASCAL
%state ZZ_IN_PASCAL_COMMENT
%state ZZ_IN_PASCAL_COMMENT_TEX_GROUP
%state ZZ_IN_PASCAL_COMMENT_QUOTED
%state ZZ_IN_PASCAL_COMMENT_DOUBLE_QUOTED
%state ZZ_IN_PASCAL_STRING
%state ZZ_IN_PREPROCESSED_STRING

WHITE_SPACE = " " | \t | \f | \R
//IDENTIFIER = [a-zA-Z] [0-9a-zA-Z_]+

%%
<YYINITIAL> {
    "%" [^\r\n]*      { return WebElementTypes.COMMENT; }
    "@@"              { return WebElementTypes.TEX_PART; }
    "@" {WHITE_SPACE} { yybegin(ZZ_TEX_PART); return WebElementTypes.CONTROL_CODE_UNSTARRED_MODULE; }
    "@*"              { yybegin(ZZ_TEX_PART); return WebElementTypes.CONTROL_CODE_STARRED_MODULE; }
    "@!"              { return WebElementTypes.COMMENT; }
    "@?"              { return WebElementTypes.COMMENT; }
    {WHITE_SPACE}+    { return TokenType.WHITE_SPACE; }
    [^]               { return WebElementTypes.TEX_PART; }
}

<ZZ_TEX_PART> {
    "%" [^\r\n]*      { return WebElementTypes.COMMENT; }
    "@@"              { return WebElementTypes.TEX_PART; }
    "@" {WHITE_SPACE} { return WebElementTypes.CONTROL_CODE_UNSTARRED_MODULE; }
    "@*"              { return WebElementTypes.CONTROL_CODE_STARRED_MODULE; }
    "@d"              { yybegin(ZZ_DEFINITION_PART); return WebElementTypes.CONTROL_CODE_MACRO_DEFINITION; }
    "@f"              { yybegin(ZZ_DEFINITION_PART); return WebElementTypes.CONTROL_CODE_FORMAT_DEFINITION; }
    "@p"              { yybegin(ZZ_PASCAL_PART); return WebElementTypes.CONTROL_CODE_UNNAMED_PASCAL; }
    "@<"              { yybegin(ZZ_PASCAL_NAME_TEX_FRAGMENT); return WebElementTypes.CONTROL_CODE_NAMED_PASCAL; }
    "@!"              { return WebElementTypes.COMMENT; }
    "@?"              { return WebElementTypes.COMMENT; }
    "@^"              { yybegin(ZZ_INDEX_HINT_IN_TEX); return WebElementTypes.CONTROL_CODE_AT_CARET; }
    "@."              { yybegin(ZZ_INDEX_HINT_IN_TEX); return WebElementTypes.CONTROL_CODE_AT_CARET; }
    "@:"              { yybegin(ZZ_INDEX_HINT_IN_TEX); return WebElementTypes.CONTROL_CODE_AT_COLON; }
    "@t"              { yybegin(ZZ_INDEX_HINT_IN_TEX); return WebElementTypes.CONTROL_CODE_AT_T; }
    "@="              { yybegin(ZZ_INDEX_HINT_IN_TEX); return WebElementTypes.CONTROL_CODE_AT_EQ; }
    {WHITE_SPACE}+    { return TokenType.WHITE_SPACE; }
    [^]               { return WebElementTypes.TEX_PART; }
}

<ZZ_PASCAL_NAME_TEX_FRAGMENT> {
    "@>="          { yybegin(ZZ_PASCAL_PART); return WebElementTypes.CONTROL_CODE_PASCAL_DEFINITION; }
    "@>"           { yybegin(ZZ_PASCAL_PART); return WebElementTypes.CONTROL_CODE_AT_GT; }
    [^]            { return WebElementTypes.TEX_FRAGMENT; }
}

<ZZ_INDEX_HINT_IN_TEX> {
    "@>"           { yybegin(ZZ_TEX_PART); return WebElementTypes.CONTROL_CODE_AT_GT; }
    [^]            { return WebElementTypes.INDEX_HINT_TEXT; }
}

<ZZ_INDEX_HINT_IN_PASCAL> {
    "@>"           { yybegin(ZZ_PASCAL_PART); return WebElementTypes.CONTROL_CODE_AT_GT; }
    [^]            { return WebElementTypes.INDEX_HINT_TEXT; }
}

<ZZ_DEFINITION_PART> {
    "@@"              { return WebElementTypes.DEFINITION_PART; }
    "@" {WHITE_SPACE} { yybegin(ZZ_TEX_PART); return WebElementTypes.CONTROL_CODE_UNSTARRED_MODULE; }
    "@*"              { yybegin(ZZ_TEX_PART); return WebElementTypes.CONTROL_CODE_STARRED_MODULE; }
    "@d"              { return WebElementTypes.CONTROL_CODE_MACRO_DEFINITION; }
    "@f"              { return WebElementTypes.CONTROL_CODE_FORMAT_DEFINITION; }
    "@p"              { yybegin(ZZ_PASCAL_PART); return WebElementTypes.CONTROL_CODE_UNNAMED_PASCAL; }
    "@<"              { yybegin(ZZ_PASCAL_NAME_TEX_FRAGMENT); return WebElementTypes.CONTROL_CODE_NAMED_PASCAL; }
    {WHITE_SPACE}+    { return TokenType.WHITE_SPACE; }
    [^]               { return WebElementTypes.DEFINITION_PART; }
}

<ZZ_PASCAL_PART> {
    "@@"              { return WebElementTypes.PASCAL_PART; }
    "@" {WHITE_SPACE} { yybegin(ZZ_TEX_PART); return WebElementTypes.CONTROL_CODE_UNSTARRED_MODULE; }
    "@*"              { yybegin(ZZ_TEX_PART); return WebElementTypes.CONTROL_CODE_STARRED_MODULE; }
    "@p"              { return WebElementTypes.CONTROL_CODE_UNNAMED_PASCAL; }
    "@<"              { yybegin(ZZ_PASCAL_NAME_TEX_FRAGMENT); return WebElementTypes.CONTROL_CODE_NAMED_PASCAL; }
    "@&"              { return WebElementTypes.COMMENT; }
    "@!"              { return WebElementTypes.COMMENT; }
    "@?"              { return WebElementTypes.COMMENT; }
    "@;"              { return WebElementTypes.COMMENT; }
    "@,"              { return WebElementTypes.COMMENT; }
    "@/"              { return WebElementTypes.COMMENT; }
    "@|"              { return WebElementTypes.COMMENT; }
    "@#"              { return WebElementTypes.COMMENT; }
    "@;"              { return WebElementTypes.COMMENT; }

// TODO
//    "and"             { return WebElementTypes.PASCAL_KEYWORD_AND; }
//    "array"           { return WebElementTypes.PASCAL_KEYWORD_ARRAY; }
//    "begin"           { return WebElementTypes.PASCAL_KEYWORD_BEGIN; }
//    "case"            { return WebElementTypes.PASCAL_KEYWORD_CASE; }
//    "const"           { return WebElementTypes.PASCAL_KEYWORD_CONST; }
//    "div"             { return WebElementTypes.PASCAL_KEYWORD_DIV; }
//    "do"              { return WebElementTypes.PASCAL_KEYWORD_DO; }
//    "downto"          { return WebElementTypes.PASCAL_KEYWORD_DOWNTO; }
//    "else"            { return WebElementTypes.PASCAL_KEYWORD_ELSE; }
//    "end"             { return WebElementTypes.PASCAL_KEYWORD_END; }
//    "file"            { return WebElementTypes.PASCAL_KEYWORD_FILE; }
//    "for"             { return WebElementTypes.PASCAL_KEYWORD_FOR; }
//    "function"        { return WebElementTypes.PASCAL_KEYWORD_FUNCTION; }
//    "goto"            { return WebElementTypes.PASCAL_KEYWORD_GOTO; }
//    "if"              { return WebElementTypes.PASCAL_KEYWORD_IF; }
//    "in"              { return WebElementTypes.PASCAL_KEYWORD_IN; }
//    "label"           { return WebElementTypes.PASCAL_KEYWORD_LABEL; }
//    "mod"             { return WebElementTypes.PASCAL_KEYWORD_MOD; }
//    "nil"             { return WebElementTypes.PASCAL_KEYWORD_NIL; }
//    "not"             { return WebElementTypes.PASCAL_KEYWORD_NOT; }
//    "of"              { return WebElementTypes.PASCAL_KEYWORD_OF; }
//    "or"              { return WebElementTypes.PASCAL_KEYWORD_OR; }
//    "packed"          { return WebElementTypes.PASCAL_KEYWORD_PACKED; }
//    "procedure"       { return WebElementTypes.PASCAL_KEYWORD_PROCEDURE; }
//    "program"         { return WebElementTypes.PASCAL_KEYWORD_PROGRAM; }
//    "record"          { return WebElementTypes.PASCAL_KEYWORD_RECORD; }
//    "repeat"          { return WebElementTypes.PASCAL_KEYWORD_REPEAT; }
//    "set"             { return WebElementTypes.PASCAL_KEYWORD_SET; }
//    "then"            { return WebElementTypes.PASCAL_KEYWORD_THEN; }
//    "to"              { return WebElementTypes.PASCAL_KEYWORD_TO; }
//    "type"            { return WebElementTypes.PASCAL_KEYWORD_TYPE; }
//    "until"           { return WebElementTypes.PASCAL_KEYWORD_UNTIL; }
//    "var"             { return WebElementTypes.PASCAL_KEYWORD_VAR; }
//    "while"           { return WebElementTypes.PASCAL_KEYWORD_WHILE; }
//    "with"            { return WebElementTypes.PASCAL_KEYWORD_WITH; }

    "@^"              { yybegin(ZZ_INDEX_HINT_IN_PASCAL); return WebElementTypes.CONTROL_CODE_AT_CARET; }
    "@."              { yybegin(ZZ_INDEX_HINT_IN_PASCAL); return WebElementTypes.CONTROL_CODE_AT_CARET; }
    "@:"              { yybegin(ZZ_INDEX_HINT_IN_PASCAL); return WebElementTypes.CONTROL_CODE_AT_COLON; }
    "@t"              { yybegin(ZZ_INDEX_HINT_IN_PASCAL); return WebElementTypes.CONTROL_CODE_AT_T; }
    "@="              { yybegin(ZZ_INDEX_HINT_IN_PASCAL); return WebElementTypes.CONTROL_CODE_AT_EQ; }

    "@'" [0-9]+       { return WebElementTypes.OCTAL_CONSTANT; }
    "@\"" ([A-Za-z0-9])+ {
          return WebElementTypes.HEX_CONSTANT;
    }
    \'                { yybegin(ZZ_IN_PASCAL_STRING); }
    \"                { yybegin(ZZ_IN_PREPROCESSED_STRING); }
    "{"               { yybegin(ZZ_IN_PASCAL_COMMENT); }
    {WHITE_SPACE}+    { return TokenType.WHITE_SPACE; }
    [^]               { return WebElementTypes.PASCAL_PART; }
}

<ZZ_IN_PASCAL_STRING> {
    \'\'           {}
    \'             { yybegin(ZZ_PASCAL_PART); return WebElementTypes.PASCAL_STRING; }
    <<EOF>>        { yybegin(ZZ_PASCAL_PART); return WebElementTypes.PASCAL_STRING; }
    [^\r\n]        {}
}

<ZZ_IN_PREPROCESSED_STRING> {
    \"\"           {}
    \"             { yybegin(ZZ_PASCAL_PART); return WebElementTypes.PREPROCESSED_STRING; }
    <<EOF>>        { yybegin(ZZ_PASCAL_PART); return WebElementTypes.PREPROCESSED_STRING; }
    [^\r\n]        {}
}

<ZZ_IN_PASCAL_COMMENT> {
    // special handlings for webmac macros
    "\\}"          {}

    // workaround for braces inside the comment.
    // In pdftex.web, nested braces appears used to appear inside the quotation so we apply
    // special rule for it so that the comment part doesn't end accidentally
    "``"           { yybegin(ZZ_IN_PASCAL_COMMENT_DOUBLE_QUOTED); }
    "`"            { yybegin(ZZ_IN_PASCAL_COMMENT_QUOTED); }

    // workaround for TeX group inside the comment. Only supports single-nest for now
    "{"            { yybegin(ZZ_IN_PASCAL_COMMENT_TEX_GROUP); }
    "}"            { yybegin(ZZ_PASCAL_PART); return WebElementTypes.COMMENT; }
    <<EOF>>        { yybegin(ZZ_PASCAL_PART); return WebElementTypes.COMMENT; }
    [^]            {}
}

<ZZ_IN_PASCAL_COMMENT_TEX_GROUP> {
    "}"            { yybegin(ZZ_IN_PASCAL_COMMENT); }
    <<EOF>>        { yybegin(ZZ_IN_PASCAL_COMMENT); }
    [^]            {}
}

<ZZ_IN_PASCAL_COMMENT_QUOTED> {
    \'             { yybegin(ZZ_IN_PASCAL_COMMENT); }
    <<EOF>>        { yybegin(ZZ_IN_PASCAL_COMMENT); }
    [^]            {}
}

<ZZ_IN_PASCAL_COMMENT_DOUBLE_QUOTED> {
    \'\'           { yybegin(ZZ_IN_PASCAL_COMMENT); }
    <<EOF>>        { yybegin(ZZ_IN_PASCAL_COMMENT); }
    [^]            {}
}

// catch all
[^] { return TokenType.BAD_CHARACTER; }

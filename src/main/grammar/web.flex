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

%state ZZ_WEB_DEFAULT
%state ZZ_TEX_PART
%state ZZ_DEFINITION_PART
%state ZZ_PASCAL_PART

%%
<YYINITIAL> {
    ("@" " "+) | "@*" {
          yybegin(ZZ_WEB_DEFAULT);
          return WebElementTypes.MODULE_BEGIN;
    }

    [^] {
          return WebElementTypes.COMMENT;
    }
}

<ZZ_WEB_DEFAULT> {
    ("@" " "+) | "@*" {
          return WebElementTypes.MODULE_BEGIN;
    }

    "@d" | "@f" {
          yybegin(ZZ_DEFINITION_PART);
          return WebElementTypes.DEFINITION_PART_BEGIN;
    }

    "@p" | "@<" {
          yybegin(ZZ_PASCAL_PART);
          return WebElementTypes.PASCAL_PART_BEGIN;
    }

    [^] {
          return WebElementTypes.TEX_PART;
    }
}

<ZZ_DEFINITION_PART> {
    ("@" " "+) | "@*" {
          yybegin(ZZ_WEB_DEFAULT);
          return WebElementTypes.MODULE_BEGIN;
    }

    "@p" | "@<" {
          yybegin(ZZ_PASCAL_PART);
          return WebElementTypes.PASCAL_PART_BEGIN;
    }

    [^] {
          return WebElementTypes.DEFINITION_PART;
    }
}

<ZZ_PASCAL_PART> {
    ("@" " "+) | "@*" {
          yybegin(ZZ_WEB_DEFAULT);
          return WebElementTypes.MODULE_BEGIN;
    }

    [^] {
          return WebElementTypes.PASCAL_PART;
    }
}

// catch all
[^] { return TokenType.BAD_CHARACTER; }

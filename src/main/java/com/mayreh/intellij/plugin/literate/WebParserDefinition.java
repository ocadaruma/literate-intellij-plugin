package com.mayreh.intellij.plugin.literate;

import org.jetbrains.annotations.NotNull;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.mayreh.intellij.plugin.literate.lexer.WebLexer;
import com.mayreh.intellij.plugin.literate.parser.WebParser;
import com.mayreh.intellij.plugin.literate.psi.WebElementTypes;

public class WebParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(WebLanguage.INSTANCE);
    public static final TokenSet COMMENT_TOKENS = TokenSet.create(
            WebElementTypes.COMMENT);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new WebLexer();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new WebParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return COMMENT_TOKENS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return WebElementTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new WebFile(viewProvider);
    }
}

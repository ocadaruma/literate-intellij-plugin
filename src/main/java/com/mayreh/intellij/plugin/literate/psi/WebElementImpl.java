package com.mayreh.intellij.plugin.literate.psi;

import org.jetbrains.annotations.NotNull;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public class WebElementImpl extends ASTWrapperPsiElement implements WebElement {
    protected WebElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}

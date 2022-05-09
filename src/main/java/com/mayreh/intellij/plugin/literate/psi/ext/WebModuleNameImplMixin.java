package com.mayreh.intellij.plugin.literate.psi.ext;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import com.mayreh.intellij.plugin.literate.psi.WebElementImpl;
import com.mayreh.intellij.plugin.literate.psi.WebModuleName;

public abstract class WebModuleNameImplMixin extends WebElementImpl implements WebModuleName {
    protected WebModuleNameImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @Nullable PsiElement getNameIdentifier() {
        return getTexFragment();
    }

    @Override
    public String getName() {
        return WebPsiUtils.stripAbbreviation(getTexFragment().getText());
    }

    @Override
    public PsiElement setName(@NotNull String name) {
        throw new IncorrectOperationException("Rename isn't supported for now");
    }
}

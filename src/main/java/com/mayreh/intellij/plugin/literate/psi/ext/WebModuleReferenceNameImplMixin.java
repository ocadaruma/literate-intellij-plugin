package com.mayreh.intellij.plugin.literate.psi.ext;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiPolyVariantReferenceBase;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.util.PsiTreeUtil;
import com.mayreh.intellij.plugin.literate.psi.WebModuleName;
import com.mayreh.intellij.plugin.literate.psi.WebModuleReferenceName;
import com.mayreh.intellij.plugin.literate.psi.impl.WebModuleNameImpl;

public abstract class WebModuleReferenceNameImplMixin
        extends WebModuleNameImpl implements WebModuleReferenceName {
    protected WebModuleReferenceNameImplMixin(ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
        return new WebModuleReference(this);
    }

    @Override
    public @NotNull String getReferenceName() {
        return WebPsiUtils.stripAbbreviation(getTexFragment().getText());
    }

    public static class WebModuleReference extends PsiPolyVariantReferenceBase<WebModuleReferenceName> {
        public WebModuleReference(@NotNull WebModuleReferenceName psiElement) {
            super(psiElement, new TextRange(0, psiElement.getTextLength()));
        }

        @Override
        public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
            return PsiTreeUtil.findChildrenOfType(
                    getElement().getContainingFile(),
                    WebModuleName.class)
                              .stream()
                              .filter(name -> prefixEquals(
                                      name.getName(), getElement().getReferenceName()))
                              .map(PsiElementResolveResult::new)
                              .toArray(ResolveResult[]::new);
        }
    }

    private static boolean prefixEquals(@Nullable String left, @Nullable String right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.startsWith(right)) {
            return true;
        }
        if (right.startsWith(left)) {
            return true;
        }
        return false;
    }
}

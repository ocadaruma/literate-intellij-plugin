package com.mayreh.intellij.plugin.literate.psi.ext;

import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.mayreh.intellij.plugin.literate.psi.WebElement;

public interface WebModuleNameElement extends WebElement,
                                              PsiNameIdentifierOwner,
                                              NavigatablePsiElement {
}

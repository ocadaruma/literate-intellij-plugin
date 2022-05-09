package com.mayreh.intellij.plugin.literate.psi.ext;

import org.jetbrains.annotations.NotNull;

import com.mayreh.intellij.plugin.literate.psi.WebElement;

public interface WebModuleReferenceNameElement extends WebElement {
    @NotNull String getReferenceName();
}

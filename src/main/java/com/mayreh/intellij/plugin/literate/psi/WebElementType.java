package com.mayreh.intellij.plugin.literate.psi;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import com.mayreh.intellij.plugin.literate.WebLanguage;

public class WebElementType extends IElementType {
    public WebElementType(@NonNls @NotNull String debugName) {
        super(debugName, WebLanguage.INSTANCE);
    }
}

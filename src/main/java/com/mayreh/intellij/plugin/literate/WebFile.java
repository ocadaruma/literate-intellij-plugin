package com.mayreh.intellij.plugin.literate;

import org.jetbrains.annotations.NotNull;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;

public class WebFile extends PsiFileBase {
    public WebFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, WebLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return WebFileType.INSTANCE;
    }
}

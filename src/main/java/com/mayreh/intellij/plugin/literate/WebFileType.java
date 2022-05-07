package com.mayreh.intellij.plugin.literate;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;

public class WebFileType extends LanguageFileType {
    public static final WebFileType INSTANCE = new WebFileType();

    private WebFileType() {
        super(WebLanguage.INSTANCE);
    }

    @Override
    public @NotNull String getName() {
        return "WEB";
    }

    @Override
    public @NotNull String getDescription() {
        return "WEB literate programming language";
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "web";
    }

    @Override
    public Icon getIcon() {
        return AllIcons.FileTypes.Any_type;
    }
}

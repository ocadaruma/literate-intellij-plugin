package com.mayreh.intellij.plugin.literate;

import com.intellij.lang.Language;

public class WebLanguage extends Language {
    public static final WebLanguage INSTANCE = new WebLanguage();

    private WebLanguage() {
        super("WEB");
    }
}

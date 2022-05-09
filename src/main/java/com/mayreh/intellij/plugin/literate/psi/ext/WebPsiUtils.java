package com.mayreh.intellij.plugin.literate.psi.ext;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WebPsiUtils {
    public static @NotNull String stripAbbreviation(@NotNull String string) {
        return string.endsWith("...") ? string.substring(0, string.length() - 3) : string;
    }
}

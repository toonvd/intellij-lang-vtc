package com.varnish.intellij.vtc;

import com.intellij.lang.Language;

public class VtcLanguage extends Language {
    public static final VtcLanguage INSTANCE = new VtcLanguage();

    private VtcLanguage() {
        super("VTC");
    }
}
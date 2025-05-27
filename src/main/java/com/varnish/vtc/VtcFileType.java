package com.varnish.vtc;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class VtcFileType extends LanguageFileType {
    public static final VtcFileType INSTANCE = new VtcFileType();

    private VtcFileType() {
        super(VtcLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Varnish Test Case";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Varnish Test Case file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "vtc";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return VtcIcons.FILE;
    }
}
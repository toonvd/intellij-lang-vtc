package com.varnish.vtc;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class VtcTokenType extends IElementType {
    public VtcTokenType(@NotNull @NonNls String debugName) {
        super(debugName, VtcLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "VtcTokenType." + super.toString();
    }
}
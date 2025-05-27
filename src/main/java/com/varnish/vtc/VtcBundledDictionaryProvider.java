package com.varnish.vtc;

import com.intellij.spellchecker.BundledDictionaryProvider;

public class VtcBundledDictionaryProvider implements BundledDictionaryProvider {
    @Override
    public String[] getBundledDictionaries() {
        return new String[]{"/dictionaries/vtc.dic"};
    }
}
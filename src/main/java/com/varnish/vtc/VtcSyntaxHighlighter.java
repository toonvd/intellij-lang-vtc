package com.varnish.vtc;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class VtcSyntaxHighlighter extends SyntaxHighlighterBase {
    // Text attribute keys
    public static final TextAttributesKey COMMENT = 
            createTextAttributesKey("VTC_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey KEYWORD = 
            createTextAttributesKey("VTC_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey COMMAND = 
            createTextAttributesKey("VTC_COMMAND", DefaultLanguageHighlighterColors.INSTANCE_METHOD);
    public static final TextAttributesKey STRING = 
            createTextAttributesKey("VTC_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER = 
            createTextAttributesKey("VTC_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey IDENTIFIER = 
            createTextAttributesKey("VTC_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey OPERATOR = 
            createTextAttributesKey("VTC_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey BRACE = 
            createTextAttributesKey("VTC_BRACE", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey BAD_CHARACTER = 
            createTextAttributesKey("VTC_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);
    public static final TextAttributesKey SHELL_SCRIPT = 
            createTextAttributesKey("VTC_SHELL_SCRIPT", DefaultLanguageHighlighterColors.FUNCTION_CALL);

    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] COMMAND_KEYS = new TextAttributesKey[]{COMMAND};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] BRACE_KEYS = new TextAttributesKey[]{BRACE};
    private static final TextAttributesKey[] SHELL_SCRIPT_KEYS = new TextAttributesKey[]{SHELL_SCRIPT};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new VtcLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(VtcTokenTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(VtcTokenTypes.KEYWORD)) {
            return KEYWORD_KEYS;
        } else if (tokenType.equals(VtcTokenTypes.COMMAND)) {
            return COMMAND_KEYS;
        } else if (tokenType.equals(VtcTokenTypes.STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(VtcTokenTypes.NUMBER)) {
            return NUMBER_KEYS;
        } else if (tokenType.equals(VtcTokenTypes.IDENTIFIER)) {
            return IDENTIFIER_KEYS;
        } else if (tokenType.equals(VtcTokenTypes.OPERATOR)) {
            return OPERATOR_KEYS;
        } else if (tokenType.equals(VtcTokenTypes.BRACE)) {
            return BRACE_KEYS;
        } else if (tokenType.equals(VtcTokenTypes.SHELL_SCRIPT)) {
            return SHELL_SCRIPT_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}

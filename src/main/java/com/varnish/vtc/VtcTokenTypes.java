package com.varnish.vtc;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface VtcTokenTypes {
    // Basic types
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

    // Comments
    IElementType COMMENT = new VtcTokenType("COMMENT");

    // Keywords and commands
    IElementType KEYWORD = new VtcTokenType("KEYWORD");
    IElementType COMMAND = new VtcTokenType("COMMAND");

    // Identifiers and literals
    IElementType IDENTIFIER = new VtcTokenType("IDENTIFIER");
    IElementType STRING = new VtcTokenType("STRING");
    IElementType NUMBER = new VtcTokenType("NUMBER");

    // Operators and braces
    IElementType OPERATOR = new VtcTokenType("OPERATOR");
    IElementType BRACE = new VtcTokenType("BRACE");

    // Shell script content
    IElementType SHELL_SCRIPT = new VtcTokenType("SHELL_SCRIPT");

    // Token sets
    TokenSet COMMENTS = TokenSet.create(COMMENT);
    TokenSet WHITESPACES = TokenSet.create(WHITE_SPACE);
    TokenSet STRINGS = TokenSet.create(STRING);
    TokenSet KEYWORDS = TokenSet.create(KEYWORD);
    TokenSet COMMANDS = TokenSet.create(COMMAND);
}

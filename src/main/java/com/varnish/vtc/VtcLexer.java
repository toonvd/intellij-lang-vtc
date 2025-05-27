package com.varnish.vtc;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NonNls;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class VtcLexer extends LexerBase {
    private CharSequence buffer;
    private int startOffset;
    private int endOffset;
    private int position;
    private int tokenStart;
    private int tokenEnd;
    private IElementType tokenType;
    private int state;

    // Lexer states
    private static final int NORMAL = 0;
    private static final int IN_SHELL_BLOCK = 1;

    // Common VTC keywords and commands
    @NonNls
    private static final Set<String> KEYWORDS = new HashSet<>(
            //noinspection SpellCheckingInspection
            Arrays.asList(
                "varnishtest", "server", "client", "varnish", "shell", "feature", "barrier"
            )
    );


    @NonNls
    private static final Set<String> COMMANDS = new HashSet<>(
            //noinspection SpellCheckingInspection
            Arrays.asList(
                "rxreq", "txresp", "txreq", "rxresp", "expect", "send", "recv", "start", "stop", "wait", "close", "accept"
            )
    );

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.buffer = buffer;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.position = startOffset;
        this.state = initialState;
        advance();
    }

    private boolean isClosingBraceOnItsOwnLine(int pos) {
        if (pos >= endOffset || buffer.charAt(pos) != '}') {
            return false;
        }

        // Look backwards to find start of line
        int lineStart = pos;
        while (lineStart > startOffset && buffer.charAt(lineStart - 1) != '\n') {
            lineStart--;
        }

        // Check if there's only whitespace before the brace
        for (int i = lineStart; i < pos; i++) {
            if (!Character.isWhitespace(buffer.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void advance() {
        tokenStart = position;

        if (position >= endOffset) {
            tokenType = null;
            tokenEnd = position;
            return;
        }

        char c = buffer.charAt(position);

        if (state == IN_SHELL_BLOCK) {
            // Find the end of the shell block content
            int current = position;

            // Skip initial newline if present
            if (current < endOffset && buffer.charAt(current) == '\n') {
                current++;
            }

            while (current < endOffset) {
                char currentChar = buffer.charAt(current);
                if (currentChar == '}') {
                    // Check if it's a closing brace on its own line
                    if (isClosingBraceOnItsOwnLine(current)) {
                        if (current > position) {
                            // Emit shell content up to the line with closing brace
                            tokenType = VtcTokenTypes.SHELL_SCRIPT;
                            tokenEnd = current;
                            position = current;
                            return;
                        } else {
                            // Just emit the closing brace and exit shell block state
                            tokenType = VtcTokenTypes.BRACE;
                            tokenEnd = current + 1;
                            position = tokenEnd;
                            state = NORMAL;
                            return;
                        }
                    }
                }
                current++;
            }

            // If we reached here, consume all remaining content as shell script
            if (current > position) {
                tokenType = VtcTokenTypes.SHELL_SCRIPT;
                tokenEnd = current;
                position = current;
            } else {
                tokenType = VtcTokenTypes.SHELL_SCRIPT;
                tokenEnd = endOffset;
                position = endOffset;
            }
            return;
        }

        // Handle whitespace
        if (Character.isWhitespace(c)) {
            while (position + 1 < endOffset && Character.isWhitespace(buffer.charAt(position + 1))) {
                position++;
            }
            tokenType = VtcTokenTypes.WHITE_SPACE;
            tokenEnd = position + 1;
            position = tokenEnd;
            return;
        }

        // Handle comments
        if (c == '#') {
            while (position + 1 < endOffset && buffer.charAt(position + 1) != '\n') {
                position++;
            }
            tokenType = VtcTokenTypes.COMMENT;
            tokenEnd = position + 1;
            position = tokenEnd;
            return;
        }

        // Handle strings
        if (c == '"' || c == '\'') {
            char quote = c;
            position++;
            while (position < endOffset) {
                if (buffer.charAt(position) == quote && buffer.charAt(position - 1) != '\\') {
                    position++;
                    break;
                }
                position++;
            }
            tokenType = VtcTokenTypes.STRING;
            tokenEnd = position;
            position = tokenEnd;
            return;
        }

        // Handle braces
        if (c == '{' || c == '}' || c == '(' || c == ')' || c == '[' || c == ']') {
            // Check if we're entering a shell block
            if (c == '{') {
                // Look back for "shell" keyword
                String prevWord = getPreviousWord();
                if ("shell".equals(prevWord)) {
                    tokenType = VtcTokenTypes.BRACE;
                    tokenEnd = position + 1;
                    position = tokenEnd;
                    state = IN_SHELL_BLOCK;
                    return;
                }
            }

            tokenType = VtcTokenTypes.BRACE;
            tokenEnd = position + 1;
            position = tokenEnd;
            return;
        }

        // Handle operators
        if (c == '=' || c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '>' ||
                c == '<' || c == '!' || c == '.' || c == '$') {
            tokenType = VtcTokenTypes.OPERATOR;
            tokenEnd = position + 1;
            position = tokenEnd;
            return;
        }

        // Handle identifiers, keywords, and commands
        if (Character.isLetterOrDigit(c) || c == '_') {
            while (position + 1 < endOffset &&
                    (Character.isLetterOrDigit(buffer.charAt(position + 1)) || buffer.charAt(position + 1) == '_')) {
                position++;
            }

            String word = buffer.subSequence(tokenStart, position + 1).toString();

            if (KEYWORDS.contains(word)) {
                tokenType = VtcTokenTypes.KEYWORD;
            } else if (COMMANDS.contains(word)) {
                tokenType = VtcTokenTypes.COMMAND;
            } else if (word.matches("\\d+")) {
                tokenType = VtcTokenTypes.NUMBER;
            } else {
                tokenType = VtcTokenTypes.IDENTIFIER;
            }

            tokenEnd = position + 1;
            position = tokenEnd;
            return;
        }

        // Handle bad characters
        tokenType = VtcTokenTypes.BAD_CHARACTER;
        tokenEnd = position + 1;
        position = tokenEnd;
    }

    private String getPreviousWord() {
        int lookBack = tokenStart - 1;
        // Skip whitespace
        while (lookBack > startOffset && Character.isWhitespace(buffer.charAt(lookBack))) {
            lookBack--;
        }
        int wordEnd = lookBack + 1;
        // Find word start
        while (lookBack >= startOffset && Character.isLetterOrDigit(buffer.charAt(lookBack))) {
            lookBack--;
        }
        int wordStart = lookBack + 1;

        if (wordStart < wordEnd) {
            return buffer.subSequence(wordStart, wordEnd).toString();
        }
        return "";
    }

    @Override
    public int getState() {
        return state;
    }

    @Nullable
    @Override
    public IElementType getTokenType() {
        return tokenType;
    }

    @Override
    public int getTokenStart() {
        return tokenStart;
    }

    @Override
    public int getTokenEnd() {
        return tokenEnd;
    }

    @NotNull
    @Override
    public CharSequence getBufferSequence() {
        return buffer;
    }

    @Override
    public int getBufferEnd() {
        return endOffset;
    }
}

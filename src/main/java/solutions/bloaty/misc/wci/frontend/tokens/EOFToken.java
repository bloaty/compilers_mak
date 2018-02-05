package solutions.bloaty.misc.wci.frontend.tokens;

import solutions.bloaty.misc.wci.frontend.Source;

public final class EOFToken implements Token {

    private final Source source;
    private final String text;
    private final Object value;
    private final int lineNumber;
    private final int linePosition;
    private final TokenType type;

    public EOFToken(Source source, int lineNumber, int linePosition, TokenType tokenType) {
        this.source = source;
        this.lineNumber = lineNumber;
        this.linePosition = linePosition;
        this.type = tokenType;
        this.text = "";
        this.value = null;
    }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public int getLinePosition() {
        return linePosition;
    }

    @Override
    public TokenType getType() {
        return type;
    }
}

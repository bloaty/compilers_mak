package solutions.bloaty.misc.wci.api.frontend.tokens;

import solutions.bloaty.misc.wci.api.frontend.Source;

public final class EOFToken implements RawToken<TokenType.Raw> {

    private final Source source;
    private final Object value;
    private final int lineNumber;
    private final int linePosition;

    private EOFToken(Source source, int lineNumber, int linePosition) {
        this.source = source;
        this.lineNumber = lineNumber;
        this.linePosition = linePosition;
        this.value = null;
    }

    public static EOFToken at(Source source, int lineNumber, int linePosition) {
        return new EOFToken(source, lineNumber, linePosition);
    }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public char[] getChars() {
        return new char[] {Source.EOF};
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
    public TokenType.Raw getType() {
        return CommonRawTokenTypes.EOF;
    }

}

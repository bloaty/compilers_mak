package solutions.bloaty.misc.wci.frontend;

import solutions.bloaty.misc.wci.frontend.tokens.Token;

import java.io.IOException;
import java.text.ParseException;

public abstract class SourceScanner {

    private final Source source;
    private Token currentToken;

    public SourceScanner(Source source) {
        this.source = source;
    }

    public Token currentToken() {
        return currentToken;
    }

    public Token nextToken() throws ParseException {
        currentToken = extractToken();
        return currentToken;
    }

    protected abstract Token extractToken() throws ParseException;

    private char currentChar() throws IOException {
        return source.currentChar();
    }

    private char nextChar() throws IOException {
        return source.nextChar();
    }

}

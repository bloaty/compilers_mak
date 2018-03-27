package solutions.bloaty.misc.wci.api.frontend;

import java.io.IOException;

import solutions.bloaty.misc.wci.api.frontend.tokens.RawToken;
import solutions.bloaty.misc.wci.api.frontend.tokens.TokenType;
import solutions.bloaty.misc.wci.api.frontend.tokens.Tokenizer;

public abstract class SourceScanner {
    private final Source source;
    private RawToken<TokenType.Raw> currentToken;
    private final Tokenizer tokenizer;

    public SourceScanner(Source source, Tokenizer tokenizer) {
        this.source = source;
        this.tokenizer = tokenizer;
    }

    public RawToken<TokenType.Raw> currentToken() {
        return currentToken;
    }

    public RawToken<TokenType.Raw> nextToken() throws IOException {
        currentToken = tokenizer.extract(source);
        return currentToken;
    }
}

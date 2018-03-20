package solutions.bloaty.misc.wci.api.frontend;

import solutions.bloaty.misc.wci.api.frontend.tokens.RawToken;
import solutions.bloaty.misc.wci.api.frontend.tokens.Tokenizer;

import java.io.IOException;

public abstract class SourceScanner {
    private final Source source;
    private RawToken currentToken;
    private final Tokenizer tokenizer;

    public SourceScanner(Source source, Tokenizer tokenizer) {
        this.source = source;
        this.tokenizer = tokenizer;
    }

    public RawToken currentToken() {
        return currentToken;
    }

    public RawToken nextToken() throws IOException {
        currentToken = tokenizer.extract(source);
        return currentToken;
    }
}

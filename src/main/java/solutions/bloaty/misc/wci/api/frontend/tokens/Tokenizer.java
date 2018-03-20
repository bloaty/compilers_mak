package solutions.bloaty.misc.wci.api.frontend.tokens;

import java.io.IOException;

import solutions.bloaty.misc.wci.api.frontend.Source;

public interface Tokenizer {
    <T extends TokenType.Raw> RawToken<T> extract(Source source) throws IOException;
}

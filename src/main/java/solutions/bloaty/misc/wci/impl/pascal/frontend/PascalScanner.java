package solutions.bloaty.misc.wci.impl.pascal.frontend;

import solutions.bloaty.misc.wci.api.frontend.Source;
import solutions.bloaty.misc.wci.api.frontend.SourceScanner;
import solutions.bloaty.misc.wci.api.frontend.tokens.*;

import java.io.IOException;

public class PascalScanner extends SourceScanner {

    public PascalScanner(Source source) {
        super(source, new PascalTokenizer());
    }

    private static final class PascalTokenizer implements Tokenizer {

        @Override
        public RawToken extract(Source source) throws IOException {
            char c = source.currentChar();
            int lineNumber = source.lineNumber();
            int linePosition = source.currentPosition();
            if (c == Source.EOF) {
                return EOFToken.at(source, lineNumber, linePosition);
            }
            return new RawToken() {
                @Override
                public char[] getChars() {
                    return new char[] {c};
                }

                @Override
                public TokenType getType() {
                    return CommonRawTokenTypes.PARSEABLE;
                }

                @Override
                public Source getSource() {
                    return source;
                }

                @Override
                public int getLineNumber() {
                    return lineNumber;
                }

                @Override
                public int getLinePosition() {
                    return linePosition;
                }
            };
        }

    }

}

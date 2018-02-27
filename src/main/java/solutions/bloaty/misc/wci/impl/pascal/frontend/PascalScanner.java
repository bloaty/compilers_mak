package solutions.bloaty.misc.wci.impl.pascal.frontend;

import solutions.bloaty.misc.wci.api.frontend.Source;
import solutions.bloaty.misc.wci.api.frontend.SourceScanner;
import solutions.bloaty.misc.wci.api.frontend.tokens.Token;

import java.text.ParseException;

public class PascalScanner extends SourceScanner {

    public PascalScanner(Source source) {
        super(source);
    }

    @Override
    protected Token extractToken() throws ParseException {
        return null;
    }

}

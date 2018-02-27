package solutions.bloaty.misc.wci.impl.pascal.frontend;

import solutions.bloaty.misc.wci.api.frontend.SourceParser;
import solutions.bloaty.misc.wci.api.frontend.SourceScanner;
import solutions.bloaty.misc.wci.api.frontend.tokens.EOFToken;
import solutions.bloaty.misc.wci.api.frontend.tokens.Token;
import solutions.bloaty.misc.wci.api.messages.MessageHandler;
import solutions.bloaty.misc.wci.api.messages.types.ParserSummaryMessage;

import java.text.ParseException;

public class TopDownPascalParser extends SourceParser {

    public TopDownPascalParser(SourceScanner scanner, MessageHandler messageHandler) {
        super(scanner, messageHandler);
    }

    @Override
    public void parse() throws ParseException {
        Token token;
        long startTime = System.currentTimeMillis();

        while(!((token = nextToken()) instanceof EOFToken)) {
            /* do something */
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        sendMessage(ParserSummaryMessage.builder()
                                        .setTotalLinesOfCode(token.getLineNumber())
                                        .setTotalNumberOfErrors(getErrorCount())
                                        .setTotalTimeElapsed(elapsedTime)
                                        .build());
    }

    @Override
    public int getErrorCount() {
        return 0;
    }
}

package solutions.bloaty.misc.wci.impl.pascal.frontend;

import solutions.bloaty.misc.wci.api.frontend.SourceParser;
import solutions.bloaty.misc.wci.api.frontend.SourceScanner;
import solutions.bloaty.misc.wci.api.frontend.tokens.*;
import solutions.bloaty.misc.wci.api.messages.MessageHandler;
import solutions.bloaty.misc.wci.api.messages.types.ParserSummaryMessage;

import java.io.IOException;
import java.text.ParseException;

public class TopDownPascalParser extends SourceParser {

    public TopDownPascalParser(SourceScanner scanner, MessageHandler messageHandler) {
        super(scanner, messageHandler);
    }

    @Override
    public void parse() throws ParseException, IOException {
        RawToken rawToken;
        long startTime = System.currentTimeMillis();

        for (rawToken = nextToken();
             rawToken.getType() != CommonRawTokenType.EOF;
             rawToken = nextToken()) {
            /* do something */
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        sendMessage(ParserSummaryMessage.builder()
                                        .setTotalLinesOfCode(rawToken.getLineNumber())
                                        .setTotalNumberOfErrors(getErrorCount())
                                        .setTotalTimeElapsed(elapsedTime)
                                        .build());
    }

    @Override
    public int getErrorCount() {
        return 0;
    }
}

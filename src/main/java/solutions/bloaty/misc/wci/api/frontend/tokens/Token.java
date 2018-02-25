package solutions.bloaty.misc.wci.api.frontend.tokens;

import solutions.bloaty.misc.wci.api.frontend.Source;

public interface Token {

//    private final Source source;
//    private final String text;
//    private final Object value;
//    private final int lineNumber;
//    private final int linePosition;
//    private final TokenType type;
//
//    private Token(Source source, String text, Object value, int lineNumber, int linePosition, TokenType type) {
//        this.source = source;
//        this.text = text;
//        this.value = value;
//        this.lineNumber = lineNumber;
//        this.linePosition = linePosition;
//        this.type = type;
//    }

    Source getSource();
    String getText();
    Object getValue();
    int getLineNumber();
    int getLinePosition();
    TokenType getType();

}

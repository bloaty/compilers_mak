package solutions.bloaty.misc.wci.frontend;

import solutions.bloaty.misc.wci.messages.Message;
import solutions.bloaty.misc.wci.messages.MessageHandler;
import solutions.bloaty.misc.wci.messages.MessageListener;
import solutions.bloaty.misc.wci.messages.MessageProducer;
import solutions.bloaty.misc.wci.frontend.tokens.Token;
import solutions.bloaty.misc.wci.middle.IntermediateCode;
import solutions.bloaty.misc.wci.middle.SymbolTable;

import java.text.ParseException;

public abstract class SourceParser implements MessageProducer {

    private static SymbolTable symTab;

    static {
        symTab = null;
    }

    private SourceScanner sourceScanner;
    private IntermediateCode intermediateCode;
    private final MessageHandler messageHandler;

    protected SourceParser(SourceScanner sourceScanner, MessageHandler messageHandler) {
        this.sourceScanner = sourceScanner;
        this.messageHandler = messageHandler;
        this.intermediateCode = null;
    }

    public abstract void parse() throws ParseException;

    public abstract int getErrorCount();

    public final Token currentToken() {
        return sourceScanner.currentToken();
    }

    public Token nextToken() throws ParseException {
        return sourceScanner.nextToken();
    }

    @Override
    public boolean addMessageListener(MessageListener listener) {
        return messageHandler.addListener(listener);
    }

    @Override
    public boolean removeMessageListener(MessageListener listener) {
        return messageHandler.removeListener(listener);
    }

    @Override
    public boolean sendMessage(Message message) {
        return messageHandler.sendMessage(message);
    }

}

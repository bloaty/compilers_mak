package solutions.bloaty.misc.wci.api.frontend;

import java.io.IOException;
import java.text.ParseException;

import solutions.bloaty.misc.wci.api.frontend.tokens.RawToken;
import solutions.bloaty.misc.wci.api.messages.Message;
import solutions.bloaty.misc.wci.api.messages.MessageHandler;
import solutions.bloaty.misc.wci.api.messages.MessageListener;
import solutions.bloaty.misc.wci.api.messages.MessageProducer;
import solutions.bloaty.misc.wci.api.middle.IntermediateCode;
import solutions.bloaty.misc.wci.api.middle.SymbolTable;

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

    public abstract void parse() throws ParseException, IOException;

    public abstract int getErrorCount();

    public final RawToken currentToken() {
        return sourceScanner.currentToken();
    }

    public RawToken nextToken() throws ParseException, IOException {
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
    public <T extends Message.Type> boolean sendMessage(Message<T> message) {
        return messageHandler.sendMessage(message);
    }

}

package solutions.bloaty.misc.wci.api.backend;

import solutions.bloaty.misc.wci.api.messages.Message;
import solutions.bloaty.misc.wci.api.messages.MessageHandler;
import solutions.bloaty.misc.wci.api.messages.MessageListener;
import solutions.bloaty.misc.wci.api.messages.MessageProducer;
import solutions.bloaty.misc.wci.api.middle.IntermediateCode;
import solutions.bloaty.misc.wci.api.middle.SymbolTable;

public abstract class Backend implements MessageProducer {

    private final SymbolTable symbolTable;
    private final IntermediateCode intermediateCode;
    private final MessageHandler messageHandler;

    public Backend(SymbolTable symbolTable, IntermediateCode intermediateCode, MessageHandler messageHandler) {
        this.symbolTable = symbolTable;
        this.intermediateCode = intermediateCode;
        this.messageHandler = messageHandler;
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

    public abstract void process(IntermediateCode intermediateCode, SymbolTable symbolTable);
}

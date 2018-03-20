package solutions.bloaty.misc.wci.api.messages;

public interface MessageProducer {

    boolean addMessageListener(MessageListener listener);
    boolean removeMessageListener(MessageListener listener);
    <T extends Message.Type> boolean sendMessage(Message<T> message);

}

package solutions.bloaty.misc.wci.messages;

public interface MessageProducer {

    boolean addMessageListener(MessageListener listener);
    boolean removeMessageListener(MessageListener listener);
    boolean sendMessage(Message message);

}

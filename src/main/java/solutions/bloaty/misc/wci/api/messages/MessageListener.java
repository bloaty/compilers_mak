package solutions.bloaty.misc.wci.api.messages;

public interface MessageListener {

    boolean messageReceived(Message<?> message);

}

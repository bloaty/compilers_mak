package solutions.bloaty.misc.wci.api.messages;

import java.util.ArrayList;
import java.util.List;

public abstract class MessageHandler {

    private List<MessageListener> listeners;

    public MessageHandler() {
        this.listeners = new ArrayList<>();
    }

    public boolean addListener(MessageListener listener) {
        return listeners.add(listener);
    };

    public boolean removeListener(MessageListener listener) {
        return listeners.remove(listener);
    };

    public <T extends Message.Type> boolean sendMessage(Message<T> message) {
        return notifyListeners(message);
    };

    private <T extends Message.Type> boolean notifyListeners(Message<T> message) {
        boolean ret = true;
        for (int i = 0; i < listeners.size(); i++) {
            boolean result = listeners.get(i).messageReceived(message);
            ret &= result;
        }
        return ret;
    }

}

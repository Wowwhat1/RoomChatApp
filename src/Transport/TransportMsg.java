package Transport;

import ADT.implementations.Queue;

public class TransportMsg {
    private Queue<String> message;

    public TransportMsg(){
        message = new Queue<>();
    }

    public void addMessage(String message){
        if (message.length() > 250) {
            throw new IllegalArgumentException("Message is too long. Maximum length is 250 characters.");
        }
        this.message.enQueue(message);
    }

    public String sendMessage(){
        if (message.isEmpty()) {
            return null;
        }
        return message.deQueue();
    }

    public boolean isEmpty() {
        return message.isEmpty();
    }
}

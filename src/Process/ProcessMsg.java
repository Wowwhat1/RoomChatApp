package Process;

import ADT.implementations.Stack;

public class ProcessMsg {
    private Stack<String> message;

    public ProcessMsg() {
        message = new Stack<>();
    }

    public void pushMessage(String message) {
        this.message.push(message);
    }

    public String popMessage() {
        return message.pop();
    }

    public String getMessage() {
        return message.peek();
    }

    public boolean isEmpty() {
        return message.isEmpty();
    }
}
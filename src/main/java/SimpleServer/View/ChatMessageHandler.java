package SimpleServer.View;

import javax.swing.*;
import java.awt.*;

public class ChatMessageHandler {
    private JTextArea chatHistory;

    void addToChatHistory(String msg) {
        chatHistory.append(msg + "\n");
    }

    Component createLog() {
        chatHistory = new JTextArea();
        chatHistory.setEditable(false);
        return new JScrollPane(chatHistory);
    }
}

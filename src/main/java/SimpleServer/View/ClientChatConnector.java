package SimpleServer.View;

import SimpleServer.Server.*;


public class ClientChatConnector {
    private String name;
    private boolean connected;
    private ChatServerWindow server;
    ChatServer clientS;
    ChatMessageHandler messageHandler = new ChatMessageHandler();
    ClientChatWindow clientCW = new ClientChatWindow(server);
    ChatServerController controller = new ChatServerController();
    ChatLogger chatLogger = new ChatLogger();

    public ClientChatConnector() {
        clientS = new ChatServer();
    }

    void connectToChatServer() {
        if (clientS.connectClient(this)) {
            messageHandler.addToChatHistory("Вы успешно подключились!\n");
            clientCW.topPanel.setVisible(false);
            connected = true;
            name = clientCW.usernameInput.getText();
            String chatHistory = chatLogger.getLog();
            if (chatHistory != null) {
                messageHandler.addToChatHistory(chatHistory);
            }
        } else {
            messageHandler.addToChatHistory("Подключение не удалось.");
        }
    }

    public void disconnectFromServer() {
        if (connected) {
            clientCW.topPanel.setVisible(true);
            connected = false;
            clientS.disconnectClient(this);
            messageHandler.addToChatHistory("Вы были отключены от сервера.");
        }
    }

    void sendChatMessage() {
        if (connected) {
            String text = clientCW.messageInput.getText();
            if (!text.equals("")) {
                controller.receiveMessage(name + ": " + text);
                clientCW.messageInput.setText("");
            }
        } else {
            messageHandler.addToChatHistory("Нет подключения к серверу");
        }
    }

    public void answer(String text) {
        messageHandler.addToChatHistory(text);
    }

}

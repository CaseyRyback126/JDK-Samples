package SimpleServer.Server;

import SimpleServer.View.ClientChatConnector;

import java.util.List;

public class ChatServerController {
    ChatLogger chatLogger = new ChatLogger();

    private List<ClientChatConnector> connectedClients;
    ChatServer chatServer = new ChatServer();

    public ChatServerController() {
    }

    public void receiveMessage(String message) {
        if (!chatServer.isRunning) {
            return;
        }
        message += "";
        chatLogger.addToChatLog(message);
        sendToAllClients(message);
        chatLogger.saveMessageToLog(message);
    }

    private void sendToAllClients(String message) {
        for (ClientChatConnector client : connectedClients) {
            client.answer(message);
        }
    }

}

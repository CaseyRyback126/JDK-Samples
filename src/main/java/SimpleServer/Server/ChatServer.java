package SimpleServer.Server;

import SimpleServer.View.ClientChatConnector;

public class ChatServer {
    boolean isRunning;
    ChatServerWindow serverWindow = new ChatServerWindow();

    public ChatServer() {
    }

    public boolean connectClient(ClientChatConnector client) {
        if (!isRunning) {
            return false;
        }
        serverWindow.connectedClients.add(client);
        return true;
    }

    public void disconnectClient(ClientChatConnector client) {
        serverWindow.connectedClients.remove(client);
        if (client != null) {
            client.disconnectFromServer();
        }
    }

    boolean isRunning() {
        return isRunning;
    }


}

package SimpleServer;

public class Main {
    public static void main(String[] args) {
        ChatServerWindow chatServerWindow = new ChatServerWindow();
        new ClientChatWindow(chatServerWindow);
        new ClientChatWindow(chatServerWindow);
    }
}

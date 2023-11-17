package SimpleServer.Server;

import SimpleServer.View.ClientChatConnector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServerWindow extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final String LOG_PATH = "src/SimpleServer/log.txt";

    List<ClientChatConnector> connectedClients;

    ChatLogger logger = new ChatLogger();

    ChatServer chatServer = new ChatServer();

    public ChatServerWindow() {
        connectedClients = new ArrayList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        initializeGUI();

        setVisible(true);
    }

    private void initializeGUI() {
        logger.chatLog = new JTextArea();
        add(logger.chatLog);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton startButton = new JButton("Start");
        JButton closeButton = new JButton("Stop");

        startButton.addActionListener(e -> {
            if (chatServer.isRunning()) {
                logger.addToChatLog("Сервер уже был запущен");
            } else {
                chatServer.isRunning = true;
                logger.addToChatLog("Сервер запущен!");
            }
        });

        closeButton.addActionListener(e -> {
            if (!chatServer.isRunning) {
                logger.addToChatLog("Сервер уже был остановлен");
            } else {
                chatServer.isRunning = false;
                for (ClientChatConnector client : connectedClients) {
                    chatServer.disconnectClient(client);
                }
                logger.addToChatLog("Сервер остановлен!");
            }
        });

        panel.add(startButton);
        panel.add(closeButton);
        return panel;
    }

}

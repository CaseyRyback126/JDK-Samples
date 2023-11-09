package SimpleServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ChatServerWindow extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final String LOG_PATH = "src/SimpleServer/log.txt";

    private List<ClientChatWindow> connectedClients;

    private JButton startButton, closeButton;

    private JTextArea chatLog;
    private boolean isRunning;

    ChatServerWindow() {
        connectedClients = new ArrayList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        initializeGUI();

        setVisible(true);
    }

    public boolean connectClient(ClientChatWindow client) {
        if (!isRunning) {
            return false;
        }
        connectedClients.add(client);
        return true;
    }

    public void disconnectClient(ClientChatWindow client) {
        connectedClients.remove(client);
        if (client != null) {
            client.disconnectFromServer();
        }
    }

    public void receiveMessage(String message) {
        if (!isRunning) {
            return;
        }
        message += "";
        addToChatLog(message);
        sendToAllClients(message);
        saveMessageToLog(message);
    }

    private void sendToAllClients(String message) {
        for (ClientChatWindow client : connectedClients) {
            client.answer(message);
        }
    }

    private void saveMessageToLog(String message) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(message);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLog() {
        return loadChatLog();
    }
    private void addToChatLog(String message) {
        chatLog.append(message + "\n");
    }

    private String loadChatLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initializeGUI() {
        chatLog = new JTextArea();
        add(chatLog);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        startButton = new JButton("Start");
        closeButton = new JButton("Stop");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    addToChatLog("Сервер уже был запущен");
                } else {
                    isRunning = true;
                    addToChatLog("Сервер запущен!");
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRunning) {
                    addToChatLog("Сервер уже был остановлен");
                } else {
                    isRunning = false;
                    for (ClientChatWindow client : connectedClients) {
                        disconnectClient(client);
                    }
                    addToChatLog("Сервер остановлен!");
                }
            }
        });

        panel.add(startButton);
        panel.add(closeButton);
        return panel;
    }

}

package SimpleServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientChatWindow extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private boolean connected;
    private String name;
    private ChatServerWindow server;
    private JTextArea chatHistory;

    private JTextField ipInput, portInput, usernameInput, messageInput;
    private JPasswordField password;

    private JButton connectButton, sendButton;

    private JPanel topPanel;

    public ClientChatWindow(ChatServerWindow server) {
        this.server = server;

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocation(server.getX() - 500, server.getY());

        initializeClientUI();

        setVisible(true);
    }

    public void answer(String text) {
        addToChatHistory(text);
    }

    private void connectToChatServer() {
        if (server.connectClient(this)) {
            addToChatHistory("Вы успешно подключились!\n");
            topPanel.setVisible(false);
            connected = true;
            name = usernameInput.getText();
            String chatHistory = server.getLog();
            if (chatHistory != null) {
                addToChatHistory(chatHistory);
            }
        } else {
            addToChatHistory("Подключение не удалось");
        }
    }

    public void disconnectFromServer() {
        if (connected) {
            topPanel.setVisible(true);
            connected = false;
            server.disconnectClient(this);
            addToChatHistory("Вы были отключены от сервера!");
        }
    }

    private void addToChatHistory(String msg) {
        chatHistory.append(msg + "\n");
    }

    private void sendChatMessage() {
        if (connected){
            String text = messageInput.getText();
            if (!text.equals("")){
                server.receiveMessage(name + ": " + text);
                messageInput.setText("");
            }
        } else {
            addToChatHistory("Нет подключения к серверу");
        }
    }

    private void initializeClientUI() {
        add(createTopPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    private Component createLog(){
        chatHistory = new JTextArea();
        chatHistory.setEditable(false);
        return new JScrollPane(chatHistory);
    }

    private JComponent createTopPanel() {
        topPanel = new JPanel(new GridLayout(2, 3));
        ipInput = new JTextField("127.0.0.1");
        portInput = new JTextField("4569");
        usernameInput = new JTextField("Dragovich Kravchenko");
        password = new JPasswordField("123456");
        connectButton = new JButton("login");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToChatServer();
            }
        });

        topPanel.add(ipInput);
        topPanel.add(portInput);
        topPanel.add(new JPanel());
        topPanel.add(usernameInput);
        topPanel.add(password);
        topPanel.add(connectButton);

        return topPanel;
    }

    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        messageInput = new JTextField();
        messageInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    sendChatMessage();
                }
            }
        });
        sendButton = new JButton("send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendChatMessage();
            }
        });
        panel.add(messageInput);
        panel.add(sendButton, BorderLayout.EAST);
        return panel;
    }

    @Override
    public int getDefaultCloseOperation() {
        disconnectFromServer();
        return super.getDefaultCloseOperation();
    }

}

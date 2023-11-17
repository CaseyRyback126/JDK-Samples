package SimpleServer.View;

import SimpleServer.Server.ChatServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientChatWindow extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    ClientChatConnector connector = new ClientChatConnector();
    ChatMessageHandler messageHandler = new ChatMessageHandler();

    JTextField usernameInput;
    JTextField messageInput;

    JPanel topPanel;

    public ClientChatWindow(ChatServerWindow server) {

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocation(server.getX() - 500, server.getY());

        initializeClientUI();

        setVisible(true);
    }


    private void initializeClientUI() {
        add(createTopPanel(), BorderLayout.NORTH);
        add(messageHandler.createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JComponent createTopPanel() {
        topPanel = new JPanel(new GridLayout(2, 3));
        JTextField ipInput = new JTextField("127.0.0.1");
        JTextField portInput = new JTextField("4569");
        usernameInput = new JTextField("Dragovich Kravchenko");
        JPasswordField password = new JPasswordField("123456");
        JButton connectButton = new JButton("login");
        connectButton.addActionListener(e -> connector.connectToChatServer());

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
                if (e.getKeyChar() == '\n') {
                    connector.sendChatMessage();
                }
            }
        });
        JButton sendButton = new JButton("send");
        sendButton.addActionListener(e -> connector.sendChatMessage());
        panel.add(messageInput);
        panel.add(sendButton, BorderLayout.EAST);
        return panel;
    }

    @Override
    public int getDefaultCloseOperation() {
        connector.disconnectFromServer();
        return super.getDefaultCloseOperation();
    }

}

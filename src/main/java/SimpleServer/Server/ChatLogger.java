package SimpleServer.Server;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;

import static SimpleServer.Server.ChatServerWindow.LOG_PATH;

public class ChatLogger {

    JTextArea chatLog;

    void saveMessageToLog(String message) {
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

    void addToChatLog(String message) {
        chatLog.append(message + "\n");
    }

    private String loadChatLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH)) {
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

}

package presentation;

import javax.swing.*;

public class Message {
    JFrame frame;

    /**
     * Constructor cu parametru pentru crearea unei ferestre de mesaj
     * @param message
     */
    public Message(String message) {
        frame = new JFrame();
        JOptionPane.showMessageDialog(frame, message);
    }
}

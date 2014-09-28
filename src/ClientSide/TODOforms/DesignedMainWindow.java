package ClientSide.TODOforms;

import javax.swing.*;

/**
 * Created by svt on 28.09.2014.
 */
public class DesignedMainWindow {
    private JPanel MainPanel;
    private JTextField TF_message;
    private JButton sendButton;
    private JButton clearChatButton;
    private JButton privateMessageButton;
    private JTextArea Chat;
    private JList LIST_users;
    private JLabel LBL_users;

    public DesignedMainWindow() {
        JFrame win = new JFrame("Chat");
        win.setContentPane(MainPanel);
        win.setVisible(true);
        win.pack();
        win.setLocation(100,100);
    }

    /**
     * just test
     */
    public static void main(String[] args){
        DesignedMainWindow win = new DesignedMainWindow();
//        Thread.currentThread().join();
    }
}

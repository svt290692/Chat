package ClientSide.GUI;

import ClientSide.Interfaces.Gui.Listeners.PrivateWindowListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by svt on 02.10.2014.
 */
public class PrivateWindow implements ClientSide.Interfaces.Gui.Windows.PrivateWindow {
    private JPanel mainPanel;
    private JLabel LBL_nameUser;
    private JTextArea TA_chat;
    private JTextField TF_message;
    private JButton B_send;

    PrivateWindowListener mListener;

    public PrivateWindow() {
        JFrame frame = new JFrame();
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);

        B_send.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    @Override
    public String getUserName() {
        return LBL_nameUser.getText();
    }

    @Override
    public void setUserName(String name) {
        LBL_nameUser.setText(name);
    }

    @Override
    public void addNewMessage(String message, boolean insertDate, boolean insertTime, boolean insertName) {
        StringBuilder msg = new StringBuilder("");

        long curTime = System.currentTimeMillis();
        Date curDate = new Date(curTime);

        if(insertDate){
            String date = new SimpleDateFormat("dd.MM.yyyy ").format(curTime);
            msg.append(date);
        }
        if(insertTime){
            String time = new SimpleDateFormat("ss:mm:hh").format(curTime);
            msg.append(time);
        }
        if(insertName){
            msg.append(getUserName() + ": ");
        }
        msg.append(message);
        TA_chat.append(msg.toString());
    }

    @Override
    public void clearChatArea() {
        TA_chat.setText("");
    }

    @Override
    public void setListener(PrivateWindowListener listener) {
        mListener = listener;
    }
}

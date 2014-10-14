package ClientSide.GUI;

import ClientSide.Interfaces.Gui.Listeners.PrivateWindowListener;
import ClientSide.StartDialogHandler;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by svt on 09.10.2014.
 */
public class PrivateWindow implements ClientSide.Interfaces.Gui.Windows.PrivateWindow{
    private JPanel mainPanel;
    private JTextField TF_message;
    private JButton B_history;
    private JButton B_send;
    private JLabel LBL_name;
    private JTextPane TP_chat;

    private PrivateWindowListener mListener;
    JFrame mainFrame;

    public PrivateWindow() {
        mainFrame = new JFrame();
        mainFrame.setContentPane(mainPanel);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        TF_message.getRootPane().setDefaultButton(B_send);

        TP_chat.setEditable(false);

        B_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(TF_message.getText().isEmpty() == false){
                    mListener.MessageWasInput(TF_message.getText(),LBL_name.getText().split(" ")[0]);

                    addNewMessage(TF_message.getText(),false,true, StartDialogHandler.getPassLogin(), new Color(127,255,212));
                    TF_message.setText("");
                }
            }
        });
        B_history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mListener.historyRequest(LBL_name.getText().split(" ")[0],StartDialogHandler.getPassLogin());
            }
        });
    }

    @Override
    public String getUserName() {
        return LBL_name.getText();
    }

    @Override
    public void setUserName(String name) {
        LBL_name.setText(name);
    }

    @Override
    public void addNewMessage(String message, boolean insertDate, boolean insertTime, String insertName, Color color) {
        try {
            addLine("[" + (insertDate ? makeDate() + " _ " : "")
                    + (insertTime ? makeTime() + " _ " : "")
                    + "] "+insertName + " : "
                    + message, color);
        } catch (BadLocationException ignore){}
    }

    public static String makeTime() {
        return
                new SimpleDateFormat("HH:mm:ss").
                        format(new Date(System.currentTimeMillis()));
    }

    public static String makeDate() {
        return
                new SimpleDateFormat("yyyy.MM.dd").
                        format(new Date(System.currentTimeMillis()));
    }

    @Override
    public void clearChatArea() {
        TP_chat.setText("");
    }

    private void addLine(String text,Color color) throws BadLocationException {
        TP_chat.getStyledDocument().insertString(TP_chat.getStyledDocument().getLength(), text + "\n",
                StyleContext.getDefaultStyleContext().addAttribute(SimpleAttributeSet.EMPTY,
                        StyleConstants.Background, color));
    }

    @Override
    public void setListener(PrivateWindowListener listener) {
        mListener = listener;
    }

    @Override
    public JFrame getFrame() {
        return mainFrame;
    }

    @Override
    public void show() {
        mainFrame.setVisible(true);
    }
}

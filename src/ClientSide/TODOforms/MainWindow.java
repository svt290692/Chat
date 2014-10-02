package ClientSide.TODOforms;

import ClientSide.Interfaces.MainWindowListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by svt on 02.10.2014.
 */
public class MainWindow implements ClientSide.Interfaces.Gui.MainWindow
{
    private JPanel MainPanel;
    private JButton B_sendLS;
    private JList LIST_users;
    private JButton B_Exit;
    private JButton B_logOut;

    DefaultListModel usersModel;

    MainWindowListener mListener;

    public MainWindow() {
        JFrame frame = new JFrame();
        frame.setContentPane(MainPanel);
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        usersModel = new DefaultListModel();
        LIST_users.setModel(usersModel);

        B_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        B_logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        B_sendLS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public static void main(String args[]){
        MainWindow w = new MainWindow();
//        Pattern p = Pattern.compile("<([\\w]+)>");
//        Matcher m = p.matcher("<rm><xxx><yyy>");
//
//        while (m.find()) {
//            System.out.print("Start index: " + m.start());
//            System.out.print(" End index: " + m.end() + " ");
//            System.out.println(m.group());
//        }
    }

    @Override
    public boolean addUser(Object user) {
        if(usersModel.contains(user))
            return false;
        usersModel.addElement(user);
        return true;
    }

    @Override
    public boolean addUser(Object user, int insertIndex) {
        if(usersModel.contains(user))
            return false;
        usersModel.insertElementAt(user,insertIndex);
        return true;
    }

    @Override
    public boolean removeUser(Object user) {
        return usersModel.removeElement(user);
    }

    @Override
    public boolean removeUser(int index) {
        return usersModel.removeElement(index);
    }

    @Override
    public void setListener(MainWindowListener listener) {
        mListener = listener;
    }
}

package ClientSide.GUI;

import ClientSide.Interfaces.Gui.Listeners.MainWindowListener;
import Net.NetworkClient;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

/**
 * Created by svt on 02.10.2014.
 */
public class MainWindow implements ClientSide.Interfaces.Gui.Windows.MainWindow
{
    private JPanel MainPanel;
    private JButton B_sendLS;
    private JList LIST_users;
    private JButton B_Exit;
    private JButton B_logOut;

    DefaultListModel usersModel;

    MainWindowListener mListener;

    JFrame MainFrame;

    public MainWindow() {
        MainFrame = new JFrame();
        MainFrame.setContentPane(MainPanel);
        MainFrame.pack();
        MainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        usersModel = new DefaultListModel();
        LIST_users.setModel(usersModel);

        B_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mListener.onMainWindowExit();
            }
        });
        B_logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mListener.onLogOut();
            }
        });
        B_sendLS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mListener.requestPrivateChat(usersModel.get(LIST_users.getLeadSelectionIndex()).toString().split(" ")[0]);
                System.out.println("DEBUG MESSAGE: request private chat with"
                        + usersModel.get(LIST_users.getLeadSelectionIndex()).toString().split(" ")[0]);
            }
        });
        LIST_users.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    mListener.requestPrivateChat(usersModel.get(LIST_users.getLeadSelectionIndex()).toString().split(" ")[0]);
                    System.out.println("DEBUG MESSAGE: request private chat with"
                            + usersModel.get(LIST_users.getLeadSelectionIndex()).toString().split(" ")[0]);
                }
            }
        });
    }
    public static void main(String args[]){
        new File("dir").mkdir();
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
    public void setUsersAndStatusesList(List<NetworkClient> users) {
        usersModel.clear();

        for(NetworkClient client : users){
            addUser(client.getName() + " " +(client.isOnline() ? "(online)" : "(offline)"));
        }
    }

    @Override
    public void setListener(MainWindowListener listener) {
        mListener = listener;
    }

    @Override
    public JFrame getFrame() {
        return MainFrame;
    }

    @Override
    public void show() {
        MainFrame.setVisible(true);
    }
}

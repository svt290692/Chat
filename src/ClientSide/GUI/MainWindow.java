package ClientSide.GUI;

import ClientSide.Interfaces.Gui.Listeners.MainWindowListener;
import Net.NetworkClient;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by svt on 11.10.2014.
 */
public class MainWindow implements ClientSide.Interfaces.Gui.Windows.MainWindow {
    private JPanel PANEL_main;
    private JTextField TF_message;
    private JButton B_send;
    private JList LIST_users;
    private JTextPane TP_chat;
    private JButton B_history;

    DefaultListModel listModel;

    private Color lime = new Color(50,205,50);

    MainWindowListener mListener;

    public MainWindow() {
        JFrame frame = new JFrame();
        frame.setLocation(300,300);
        frame.setContentPane(PANEL_main);
        frame.pack();
        frame.setVisible(true);

        listModel = new DefaultListModel();
        LIST_users.setModel(listModel);
        listModel.addElement("ВАСЯ (тут)");
        listModel.addElement("ПЕтя(тут)");
        listModel.addElement("Юля(там)");
        listModel.addElement("билл гейтс (там)");


        LIST_users.setCellRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value.toString().endsWith("(тут)"))
                     comp.setForeground(Color.GREEN);
                else
                    comp.setForeground(Color.BLUE);

                return comp;
            }
        });

        LIST_users.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                if(super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                }
                else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });

        addLineToChat("[11:52 для ВАСЯ] Привет Василий!",lime);
        addLineToChat("[11:52 от ВАСЯ] Привет Как дела?",Color.yellow);
        addLineToChat("[11:52 для ВАСЯ,Юля] Привет Коллеги",lime);

        TF_message.getRootPane().setDefaultButton(B_send);

        B_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(false == TF_message.getText().isEmpty())
                mListener.sendMessageTo(TF_message.getText(),LIST_users.getSelectedValuesList());
            }
        });

//        LIST_users.addMouseListener(new MouseInputAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if(mSelectionSet.contains(LIST_users.getSelectedIndex())){
//                    mSelectionSet.remove(LIST_users.getSelectedIndex());
//                }else{
//                    mSelectionSet.add(LIST_users.getSelectedIndex());
//                }
//
//                List<Integer> arr1 = new ArrayList<Integer>(mSelectionSet);
//
//                int[] arr = new int[arr1.size()];
//
//                for(int i = 0 ; i < arr1.size();i++){
//                    arr[i] = arr1.get(i);
//                }
//
//                LIST_users.setSelectedIndices(arr);
//
//            }
//        });
//        LIST_users.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if(LIST_users.isSelectedIndex(e.getFirstIndex())){
//                    LIST_users.addSelectionInterval(e.getFirstIndex(),e.getFirstIndex());
//                }
//            }
//        });
//        ListSelectionModel lm = new DefaultListSelectionModel(){
//            @Override
//            public void removeSelectionInterval(int index0, int index1) {
////                super.removeSelectionInterval(index0, index1);
//            }
//
//            @Override
//            public void removeIndexInterval(int index0, int index1) {
////                super.removeIndexInterval(index0, index1);
//            }
//
//        };
//        LIST_users.setSelectionModel(lm);
    }

    @Override
    public void outsideMessage(String message, String nameFrom, boolean insertDate, boolean insertTime){
        addLineToChat(message,Color.yellow);
    }

    @Override
    public void setListener(MainWindowListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void addUser(String name, boolean isOnline){
        listModel.addElement(name + " " + (isOnline ? "(тут)" : "(там)"));
    }

    @Override
    public void changeUserStatus(String name, boolean isOnline){
        if(listModel.contains(name + " " + "(тут)")){
            int i = listModel.indexOf(name + " " + "(тут)");
            listModel.setElementAt(name + " " + (isOnline ? "(тут)" : "(там)"),i);
        }
        else if(listModel.contains(name + " " + "(там)")){
            int i = listModel.indexOf(name + " " + "(там)");
            listModel.setElementAt(name + " " + (isOnline ? "(тут)" : "(там)"),i);
        }
    }

    @Override
    public void setUsersAndStatusesList(List<NetworkClient> users) {

    }

    @Override
    public JFrame getFrame() {
        return null;
    }

    private void addLineToChat(String text, Color backgroundColor){
        try {
            TP_chat.getStyledDocument().insertString(TP_chat.getStyledDocument().getLength(), text + "\n",
                    StyleContext.getDefaultStyleContext().addAttribute(SimpleAttributeSet.EMPTY,
                            StyleConstants.Background, backgroundColor));
        } catch (BadLocationException ignore) { }
    }

    public static void main(String args[]) throws InterruptedException {
        ClientSide.Interfaces.Gui.Windows.MainWindow win = new MainWindow();
        Thread.currentThread().join();
    }
}

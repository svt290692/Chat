package ClientSide.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by svt on 11.10.2014.
 */
public abstract class UsersList {
    private JPanel PANEL_mainPanel;
    private JComboBox CB_users;
    private JTextField TF_ID;
    private JButton B_ok;

    DefaultComboBoxModel model;

    public UsersList(){
        final JFrame frame = new JFrame();
        frame.setLocation(300,300);
        frame.setTitle("Выбор пользователя");
        frame.setContentPane(PANEL_mainPanel);
        frame.pack();
        frame.setVisible(true);

        model = new DefaultComboBoxModel();

        model.addElement("ВАСЯ");
        model.addElement("Юля");
        model.addElement("ПЕТЯ");
        model.addElement("Бил Гейтс");

        CB_users.setModel(model);
        B_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(TF_ID.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"field ID can not be empty, please enter you personal ID number",
                            "Field is empty",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    wasChoice(CB_users.getSelectedItem().toString(),TF_ID.getText());
                }
            }
        });
    }

    protected void wasChoice(String name, String ID){

    }

    public static void main(String args[]) throws InterruptedException {
        UsersList win = new UsersList(){};
        Thread.currentThread().join();
    }
}

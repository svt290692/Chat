package ClientSide.TODOforms;

import ClientSide.Interfaces.StartDialogListener;

import javax.swing.*;
import java.awt.event.*;

public class StartDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameTextField;
    private JPasswordField passwordPasswordField;
    private JTextField IPAddressTextField;
    private JTextField portTextField;
    private JLabel LBL_service;
    private JLabel LBL_name;
    private JLabel LBL_password;
    private JLabel LBL_IPAddress;
    private JLabel LBL_port;

    StartDialogListener mListener;

    public StartDialog(boolean showPort) {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        if(!showPort) portTextField.setEnabled(false);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
// add your code here

        String name = nameTextField.getText();
        char[] password = passwordPasswordField.getPassword();

        String IPAddress = IPAddressTextField.getText();

        mListener.okClicked(this,name,String(password),IPAddress,
                portTextField.isEnabled() ? portTextField.getText() : null);

        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void setListener(StartDialogListener Listener) {
        this.mListener = Listener;
    }
    /**
     * just test
     */
    public static void main(String[] args) {
        StartDialog dialog = new StartDialog(false);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

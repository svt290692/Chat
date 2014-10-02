package ClientSide.TODOforms;

import ClientSide.Interfaces.Gui.RegisterWindow;

import javax.swing.*;
import java.awt.event.*;

public class RegisterDialog extends JDialog implements RegisterWindow{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField TF_login;
    private JLabel LBL_registration;
    private JLabel LBL_login;
    private JPasswordField PF_password;
    private JLabel LBL_topPassword;
    private JLabel LBL_confirmPassword;
    private JPasswordField PF_confirmPassword;

    ActionListener mListener;

    public RegisterDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mListener.actionPerformed(e);
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

//        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        RegisterDialog dialog = new RegisterDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    public String getLogin() {
        return TF_login.getText();
    }

    @Override
    public boolean isPasswordsEquals() {
        return (new String(PF_password.getPassword()).equals(
                new String(PF_confirmPassword.getPassword())));
    }

    @Override
    public String getPassword() {
        return new String(PF_password.getPassword());
    }

    @Override
    public String getConfirmPassword() {
        return new String(PF_confirmPassword.getPassword());
    }

    @Override
    public void setListener(ActionListener listener) {
        mListener = listener;
    }
}

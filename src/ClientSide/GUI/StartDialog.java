package ClientSide.GUI;

import ClientSide.Interfaces.Gui.Listeners.StartDialogListener;
import ClientSide.Interfaces.Gui.Windows.StartWindow;

import javax.swing.*;
import java.awt.event.*;

public class StartDialog extends JDialog implements StartWindow {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField TF_login;
    private JTextField TF_password;
    private JButton B_registration;
    private JButton B_settings;
    private JLabel LBL_welcome;
    private JLabel LBL_login;
    private JLabel LBL_password;

    StartDialogListener mListener;

    public StartDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mListener.onOK();
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
        B_settings.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                mListener.settingsClicked();
            }
        });
        B_registration.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                mListener.registrationClicked();
            }
        });
    }

    private void onOK() {
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        StartDialog dialog = new StartDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    /**
     * set listener to this window
     *
     * @param listener
     */
    @Override
    public void setListener(StartDialogListener listener) {
        mListener = listener;
    }

    @Override
    public String getLoginField() {
        return TF_login.getText();
    }

    @Override
    public String getPasswordField() {
        return TF_password.getText();
    }

    @Override
    public JDialog getDialog() {
        return this;
    }

    /**
     * set text to label output
     *
     * @param text
     */
    @Override
    public void setLabel(String text) {
        LBL_welcome.setText(text);
    }
}

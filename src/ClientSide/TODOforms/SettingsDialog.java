package ClientSide.TODOforms;

import ClientSide.Interfaces.Gui.SettingsWindow;

import javax.swing.*;
import java.awt.event.*;

public class SettingsDialog extends JDialog implements SettingsWindow{
    private JPanel contentPane;



    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField TF_IP;
    private JTextField TF_port;
    private JLabel LBL_settings;
    private JLabel LBL_IP;
    private JLabel LBL_port;

    ActionListener mListener;

    public SettingsDialog() {
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
// add your code here
//        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SettingsDialog dialog = new SettingsDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    public String getIP() {
        return null;
    }

    @Override
    public String getPort() {
        return null;
    }

    @Override
    public void setTopLabel(String text) {

    }
    @Override
    public void setListener(ActionListener OkListener) {
        mListener = OkListener;
    }
}

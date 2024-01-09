package app.form.Calcolatrice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton btn1;
    private JLabel labelPassword;
    private JLabel labelUsername;
    private JButton btn2;
    private javax.swing.JPanel JPanel;

    database databs;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Login() {
        databs = new database("127.0.0.1", "dbcalcolatrice","root","");

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = new String(passwordField1.getPassword());

                if (databs.login(username, password)) {
                    JOptionPane.showMessageDialog(null, "LOGIN RIUSCITO");

                    hideForm();
                    Calcolatrice.main(username);
                } else {
                    JOptionPane.showMessageDialog(null, "LOGIN FALLITO");
                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = new String(passwordField1.getPassword());

                if (databs.registrazione(username, password)) {
                    JOptionPane.showMessageDialog(null, "REGISTRAZIONE RIUSCITA!");

                    hideForm();
                    Calcolatrice.main(username); // Open the calculator form
                } else {
                    JOptionPane.showMessageDialog(null, "REGISTRAZIONE FALLITA");
                }
            }
        });
    }


    private void hideForm(){
        //nascondo il form
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(JPanel);
        if (frame != null) {
            frame.setVisible(false);
        }
    }
}


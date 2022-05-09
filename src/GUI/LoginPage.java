package GUI;

import Service.Sys_UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private JPanel panelMain;
    private JTextField txtFUsername;
    private JPasswordField txtFPassword;
    private JButton btnLogin;
    private JButton btnForgotPassword;
    private JButton btnSignup;

    private static Sys_UserService usrSrv = new Sys_UserService();
    private static JFrame frame = new JFrame("LoginPage");

    public LoginPage() {


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtFUsername.getText();
                String password = String.valueOf(txtFPassword.getPassword());

                if (usrSrv.login(name, password)) {
                    JOptionPane.showMessageDialog(null, "Dang nhap thanh cong!");
                    //TODO: Chuyen sang trang quan ly khach hang
                }
                else {
                    JOptionPane.showMessageDialog(null, "Tai khoan hoac mat khau dang nhap sai");
                }

            }
        });


        btnForgotPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new ForgotPasswordPage().display();
            }
        });
    }


    public void display() {
        frame.setContentPane((new LoginPage().panelMain));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

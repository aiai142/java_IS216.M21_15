package GUI;

import JavaMail.JavaMailUtil;
import JavaMail.RandomStringGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPasswordPage {
    private JPanel forgotPasswordPanel;
    private JTextField txtFEmail;
    private JButton btnCode;
    private JTextField txtFCode;
    private JButton btnContinue;
    private JTextField textFUserName;
    private JTextField textFNewPassword;
    private JPasswordField txtFRetype;
    private JPanel getCodePanel;
    private JPanel setNewPasswordPanel;
    private JButton btnUpdate;


    private static JFrame frame = new JFrame("ForgotPasswordPage");



    public ForgotPasswordPage() {

        btnContinue.addActionListener(e -> {
            forgotPasswordPanel.removeAll();
            forgotPasswordPanel.add(setNewPasswordPanel);
            forgotPasswordPanel.repaint();
            forgotPasswordPanel.revalidate();
        });

        btnCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mail =  txtFEmail.getText();
                RandomStringGenerator rand = new RandomStringGenerator();
                JavaMailUtil.sendMail(mail, rand.randomAlphaNumeric(8));
                JOptionPane.showMessageDialog(null, "Email sent");
            }
        });
    }


    public void display() {
        frame.setContentPane((new ForgotPasswordPage().forgotPasswordPanel));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}

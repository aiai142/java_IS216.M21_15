/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

 import java.util.Properties ;
    import javax.mail.Session ;
    import javax.mail.PasswordAuthentication ;
    import javax.mail.Message ;
    import javax.mail.internet.InternetAddress ;
    import javax.mail.internet.MimeMessage ;
    import javax.mail.Transport ;
    import javax.mail.MessagingException ;

/**
 *
 * @author DoQuynhChi
 */
public class JavaMailUtil {

    private static final String USERNAME = "freshfood2052@gmail.com";
    private static final String PASSWORD = "farm/2052";

    public static void sendMail(String recipient, String code) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        // dang nhap gmail ngam
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        Message message = prepareMessage(session, USERNAME, recipient, code);
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public static Message prepareMessage(Session session, String USERNAME, String recipient, String code) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("freshfood2052@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient)
            );
            message.setSubject("Khôi phục mật khẩu");
            message.setText("Code khôi phục mật khẩu của bạn: "
                    + "\n\n" + code);
            return message;
        } catch (MessagingException em) {
            em.printStackTrace();
        }
        return null;
    }

}

package Web;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaEmailUnit {


    public  static  void sendEmail(String recipient) throws Exception {
        System.out.println("Start sending email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String account = "zewen1708@gmail.com";
        String password = "czw19970719";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(account, password);
            }
        });

        Message message = prepareMessage(session, account, recipient);
        Transport.send(message);
        System.out.println("Message send successfully");
    }

    private static Message prepareMessage(Session session, String account,String recipient) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(account));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
            message.setSubject("Questionnaire Conformation");
            String questionnaireInfo = getInfo();
            message.setText(questionnaireInfo);
            return message;
        } catch (Exception e) {
            Logger.getLogger(JavaEmailUnit.class.getName()).log(Level.SEVERE,"fail to prepare message",e);
        }
        return  null;
    }

    private static String getInfo() throws SQLException {
        ResultSet rs =  SQLSetUp();
        WebController webController = new WebController();
        String testResult = webController.convertToString(rs);
        return  testResult;
    }

    private static ResultSet SQLSetUp() throws SQLException {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://ivmsdb.cs17etkshc9t.us-east-1.rds.amazonaws.com;DatabaseName=sysc4806";
        String userName = "admin";
        String userPwd = "ivmsdbadmin";
        Connection conn = DriverManager.getConnection(dbURL, userName, userPwd);
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select top 1 * from Questionnaire order BY id DESC ");
        return resultSet;
    }



    public static void main(String[] args) throws Exception {
        JavaEmailUnit.sendEmail("chenzewen0505@gmail.com");
    }
}

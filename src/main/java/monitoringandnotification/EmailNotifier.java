package monitoringandnotification;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailNotifier {
    private static final String EMAIL_TO = "yeshsharma516032@gmail.com";
    private static final String EMAIL_FROM = "yesh@zasyasolutions.com";
    private static final String EMAIL_PASSWORD = "Yesh255198@";

    public void sendEmailNotification() {
        // Email notification configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Update with your SMTP server
        props.put("mail.smtp.port", "587"); // Update with your SMTP port

        // Session object for sending email
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, EMAIL_PASSWORD);
            }
        });

        try {
            // Create a message
        	
      
        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO));
            message.setSubject("Website Downtime Alert");
            message.setText("The website " + WebsiteMonitor.getWebsiteUrl() + " is currently down. Please investigate.");

            // Send the email
            Transport.send(message);

            System.out.println("Notification email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Notification email not sent");
        }
    }
}

package mobi.app.toolkit.aws.impl;

import com.amazonaws.services.simpleemail.AWSJavaMailTransport;
import mobi.app.toolkit.aws.AwsMailTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 下午4:01
 */
public class DefaultMailTools extends JavaMailSenderImpl implements AwsMailTools {
    Logger logger = LoggerFactory.getLogger(DefaultMailTools.class);
    public static final String MAIL_TRANSPORT_PROTOCOL_KEY = "mail.transport.protocol";

    private final String awsAccessKeyId;

    private final String awsSecretKey;

    private final String adminMail;


    public DefaultMailTools(String awsAccessKeyId, String awsSecretKey, String adminMail) {
        this.awsAccessKeyId = awsAccessKeyId;
        this.awsSecretKey = awsSecretKey;
        this.adminMail = adminMail;
        init();
    }


    private void init() {
        Properties props = getJavaMailProperties();
        props.setProperty(MAIL_TRANSPORT_PROTOCOL_KEY, "aws");
        props.setProperty(AWSJavaMailTransport.AWS_ACCESS_KEY_PROPERTY, awsAccessKeyId);
        props.setProperty(AWSJavaMailTransport.AWS_SECRET_KEY_PROPERTY, awsSecretKey);
        // set port to -1 to ensure that spring calls the equivalent of transport.connect().
        setPort(-1);
    }

    @Override
    protected Transport getTransport(Session session) throws NoSuchProviderException {
        return new AWSJavaMailTransport(session, null);
    }

    @Override
    public String getAdminMail() {
        return adminMail;
    }

    @Override
    public void sendMail(String to, String title, String body) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(adminMail);
        message.setText(title);
        message.setSubject(body);
        send(message);
    }
}

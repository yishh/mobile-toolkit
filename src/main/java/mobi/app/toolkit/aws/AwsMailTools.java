package mobi.app.toolkit.aws;

import org.springframework.mail.MailException;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 下午4:00
 */
public interface AwsMailTools {
    void sendMail(String to, String title, String body) throws MailException;

    String getAdminMail();
}

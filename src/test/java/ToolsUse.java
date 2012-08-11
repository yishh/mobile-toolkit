import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import mobi.app.toolkit.apple.*;
import mobi.app.toolkit.apple.impl.DefaultApnsTools;
import mobi.app.toolkit.apple.impl.DefaultAsyncApnsTools;
import mobi.app.toolkit.apple.impl.DefaultIapTools;
import mobi.app.toolkit.apple.impl.LogNotificationMonitor;
import mobi.app.toolkit.aws.AwsMailTools;
import mobi.app.toolkit.aws.AwsS3Tools;
import mobi.app.toolkit.aws.impl.DefaultMailTools;
import mobi.app.toolkit.aws.impl.DefaultS3Tools;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 下午4:31
 */
public class ToolsUse {
    public ToolsUse() throws CommunicationException, KeystoreException {
        //setup apns by your keystore, key password and production ,false will use sandbox env
        ApnsTools apnsTools = new DefaultApnsTools("aps.p12", "password", false);
        apnsTools.alert("message", "device token");

        //async apns , should set async thread number
        AsyncApnsTools asyncApnsTools = new DefaultAsyncApnsTools("aps.p12", "password", false, 12);
        asyncApnsTools.alert("message", "device token");

        // you can setup a monitor for async apns tool.such as log monitor for print push result log.
        // 2 is the log interval. TimeUnit is second
        AsyncNotificationMonitor monitor = new LogNotificationMonitor(2, asyncApnsTools);

        //Setup iap tools,  false will use sandbox env
        IapTools iapTools = new DefaultIapTools(false);
        IapReceipt receipt = iapTools.validate("your pay receipt");
        System.out.print(receipt.getStatus());



        AwsS3Tools s3Tools = new DefaultS3Tools("your accessKey", "your accessSecret");
        String url  = s3Tools.upload("bucket", "key", new byte[]{}, "image/png");
        System.out.print(url);


        AwsMailTools mailTools = new DefaultMailTools("your accessKey", "your accessSecret", "your admin mail");
        mailTools.sendMail("to address", "title", "body");
    }
}

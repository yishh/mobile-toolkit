package mobi.app.toolkit.apple;

import javapns.devices.Device;
import javapns.notification.Payload;
import javapns.notification.transmission.PushQueue;

import java.util.List;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 2:59 AM, A async apns tools, just call javapns's queue api
 */
public interface AsyncApnsTools {
    String getKeystore();

    String getPassword();

    boolean isProduction();

    void alert(String message, Object devices);

    void badge(int badge, Object devices);

    void sound(String sound, Object devices);

    void combined(String message, int badge, String sound, Object devices);

    void contentAvailable(Object devices);

    void payload(Payload payload, Object devices);

    int getThreadNumber();

    PushQueue getPushQueue();

}

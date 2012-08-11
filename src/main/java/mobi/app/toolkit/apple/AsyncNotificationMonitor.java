package mobi.app.toolkit.apple;

import javapns.notification.PushedNotification;
import javapns.notification.transmission.PushQueue;

import java.util.List;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 下午3:32
 */
public interface AsyncNotificationMonitor {
//    void handle(PushQueue queue);
    void setAsyncApnsTools(AsyncApnsTools tools);
    AsyncApnsTools getAsyncApnsTools();
    void monitor();
}

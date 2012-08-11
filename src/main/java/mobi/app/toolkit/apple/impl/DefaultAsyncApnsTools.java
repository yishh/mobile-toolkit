package mobi.app.toolkit.apple.impl;

import javapns.Push;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.devices.Devices;
import javapns.notification.NewsstandNotificationPayload;
import javapns.notification.Payload;
import javapns.notification.PushNotificationPayload;
import javapns.notification.transmission.PushQueue;
import mobi.app.toolkit.apple.AsyncApnsTools;

import java.util.List;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 下午3:09
 */
public class DefaultAsyncApnsTools implements AsyncApnsTools {

//    Logger logger = LoggerFactory.getLogger(DefaultAsyncApnsTools.class);
    final String keystore;
    final String password;
    final boolean production;
    final PushQueue pushQueue;
    final int threadNumber;


    public DefaultAsyncApnsTools(String keystore, String password, boolean production, int threadNumber) {
        this.keystore = keystore;
        this.password = password;
        this.production = production;

        this.threadNumber = threadNumber;

        try {
            pushQueue = Push.queue(ClassLoader.getSystemResourceAsStream(keystore), password, production, threadNumber);
            pushQueue.start();

        } catch (KeystoreException e) {
            throw new RuntimeException("APNS Key error", e.getCause());
        }
    }


    @Override
    public String getKeystore() {
        return keystore;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isProduction() {
        return production;
    }

    @Override
    public int getThreadNumber() {
        return threadNumber;
    }

    @Override
    public PushQueue getPushQueue() {
        return pushQueue;
    }


    @Override
    public void alert(String message, Object devices) {
        payload(PushNotificationPayload.alert(message), devices);
    }

    @Override
    public void badge(int badge, Object devices) {
        payload(PushNotificationPayload.badge(badge), devices);
    }

    @Override
    public void sound(String sound, Object devices) {
        payload(PushNotificationPayload.sound(sound), devices);
    }

    @Override
    public void combined(String message, int badge, String sound, Object devices) {
        payload(PushNotificationPayload.combined(message, badge, sound), devices);
    }

    @Override
    public void contentAvailable(Object devices) {
        payload(NewsstandNotificationPayload.contentAvailable(), devices);
    }

    @Override
    public void payload(Payload payload, Object devices) {
        List<Device> deviceList = Devices.asDevices(devices);
        for (Device device : deviceList) {
            pushQueue.add(payload, device);
        }
    }


}

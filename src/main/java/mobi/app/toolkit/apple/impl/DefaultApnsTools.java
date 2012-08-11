package mobi.app.toolkit.apple.impl;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.notification.Payload;
import javapns.notification.PushedNotification;
import mobi.app.toolkit.apple.ApnsTools;

import java.io.InputStream;
import java.util.List;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 下午2:51
 */
public class DefaultApnsTools implements ApnsTools {
    final String keystore;
    final String password;
    final boolean production;


    public DefaultApnsTools(String keystore, String password, boolean production) {
        this.keystore = keystore;
        this.password = password;
        this.production = production;
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

    private InputStream getKeystoreStream() {
        return ClassLoader.getSystemResourceAsStream(keystore);
    }

    @Override
    public List<PushedNotification> alert(String message, Object devices) throws CommunicationException, KeystoreException {
        return Push.alert(message, getKeystoreStream(), password, production, devices);
    }

    @Override
    public List<PushedNotification> badge(int badge, Object devices) throws CommunicationException, KeystoreException {
        return Push.badge(badge, getKeystoreStream(), password, production, devices);
    }

    @Override
    public List<PushedNotification> sound(String sound, Object devices) throws CommunicationException, KeystoreException {
        return Push.sound(sound, getKeystoreStream(), password, production, devices);
    }

    @Override
    public List<PushedNotification> combined(String message, int badge, String sound, Object devices) throws CommunicationException, KeystoreException {
        return Push.combined(message, badge, sound, getKeystoreStream(), password, production, devices);
    }

    @Override
    public List<PushedNotification> contentAvailable(Object devices) throws CommunicationException, KeystoreException {
        return Push.contentAvailable(getKeystoreStream(), password, production, devices);
    }

    @Override
    public List<PushedNotification> test(Object devices) throws CommunicationException, KeystoreException {
        return Push.test(getKeystoreStream(), password, production, devices);
    }

    @Override
    public List<PushedNotification> payload(Payload payload, Object devices) throws CommunicationException, KeystoreException {
        return Push.payload(payload, getKeystoreStream(), password, production, devices);
    }

    @Override
    public List<PushedNotification> payload(Payload payload, int numberOfThreads, Object devices) throws Exception {
        return Push.payload(payload, getKeystoreStream(), password, production, numberOfThreads, devices);
    }

    @Override
    public List<PushedNotification> payloads(Object payloadDevicePairs) throws CommunicationException, KeystoreException {
        return Push.payloads(getKeystoreStream(), password, production, payloadDevicePairs);
    }

    @Override
    public List<PushedNotification> payloads(int numberOfThreads, Object payloadDevicePairs) throws Exception {
        return Push.payloads(getKeystoreStream(), password, production, numberOfThreads, payloadDevicePairs);
    }

    @Override
    public List<Device> feedback() throws CommunicationException, KeystoreException {
        return Push.feedback(getKeystoreStream(), password, production);
    }


}

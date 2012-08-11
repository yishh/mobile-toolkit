package mobi.app.toolkit.apple;

import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.notification.Payload;
import javapns.notification.PushedNotification;

import java.util.List;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 2:40 AM
 *
 * A sync apns tools, just simple call javapns api!
 */
public interface ApnsTools {
    String getKeystore();

    String getPassword();

    boolean isProduction();

    List<PushedNotification> alert(String message, Object devices) throws CommunicationException, KeystoreException;

    List<PushedNotification> badge(int badge, Object devices) throws CommunicationException, KeystoreException;

    List<PushedNotification> sound(String sound, Object devices) throws CommunicationException, KeystoreException;

    List<PushedNotification> combined(String message, int badge, String sound, Object devices) throws CommunicationException, KeystoreException;

    List<PushedNotification> contentAvailable(Object devices) throws CommunicationException, KeystoreException;

    List<PushedNotification> test(Object devices) throws CommunicationException, KeystoreException;

    List<PushedNotification> payload(Payload payload, Object devices) throws CommunicationException, KeystoreException;

    List<PushedNotification> payload(Payload payload, int numberOfThreads, Object devices) throws Exception;

    List<PushedNotification> payloads(Object payloadDevicePairs) throws CommunicationException, KeystoreException;

    List<PushedNotification> payloads(int numberOfThreads, Object payloadDevicePairs) throws Exception;

    List<Device> feedback() throws CommunicationException, KeystoreException;



}

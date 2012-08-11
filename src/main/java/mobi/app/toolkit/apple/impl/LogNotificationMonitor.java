package mobi.app.toolkit.apple.impl;

import javapns.notification.PushedNotification;
import mobi.app.toolkit.apple.AsyncApnsTools;
import mobi.app.toolkit.apple.AsyncNotificationMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 3:32 AM, This monitor will log async apns's push results.
 */
public class LogNotificationMonitor implements AsyncNotificationMonitor {
    Logger logger = LoggerFactory.getLogger(LogNotificationMonitor.class);
    ScheduledExecutorService logExecutorService;
    AsyncApnsTools tools;
    int logPeriod;

    public LogNotificationMonitor(int logPeriod, AsyncApnsTools tools) {
        this.logPeriod = logPeriod;
        setAsyncApnsTools(tools);
        logExecutorService = Executors.newScheduledThreadPool(1);
        logExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                monitor();

            }
        }, logPeriod, logPeriod, TimeUnit.SECONDS);
    }


    @Override
    public void setAsyncApnsTools(AsyncApnsTools tools) {
        this.tools = tools;
    }

    @Override
    public AsyncApnsTools getAsyncApnsTools() {
        return tools;
    }

    @Override
    public void monitor() {
        if (tools == null) return;
        for (PushedNotification n : tools.getPushQueue().getPushedNotifications()) {
            logger.info("aysnc push status: {}, {} , {}",
                    new Object[]{n.getDevice(), n.isSuccessful(), n.getPayload().toString()});
        }
    }

    public void shutdown() {
        logExecutorService.shutdown();
    }
}

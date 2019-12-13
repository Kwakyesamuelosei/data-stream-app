package io.turntabl;

import com.google.common.base.Charsets;
import org.apache.flume.*;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.PollableSourceConstants;
import twitter4j.Status;
import twitter4j.User;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractSource;
import org.apache.flume.*;

import twitter4j.conf.*;
import twitter4j.*;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;


public class Main extends AbstractSource implements PollableSource, Configurable {

    MyTwitterController myTwitterController = null;
    Long backoffSleepIncrement;
    Long maxBackOffSleepInterval;

    @Override
    public void configure(Context context) {
         myTwitterController = new MyTwitterController();
         //Every 11 secs
        backoffSleepIncrement = context.getLong(PollableSourceConstants.BACKOFF_SLEEP_INCREMENT
                , (long) 10000);
        //Every 60 sec
        maxBackOffSleepInterval = context.getLong(PollableSourceConstants.MAX_BACKOFF_SLEEP
                , (long) 55000);
    }

    @Override
    public void start() {
        // Initialize the connection to the external client
    }

    @Override
    public void stop () {
        // Disconnect from external client and do any additional cleanup
        // (e.g. releasing resources or nulling-out field values) ..

    }


    @Override
    public Status process() throws EventDeliveryException {

        Status status = null;

        try {
            // This try clause includes whatever Channel/Event operations you want to do

            // Receive new data

            List<JSONObject> allTweets = myTwitterController.fetchTweets();

            List<Event> events = allTweets.stream()
                    .map(user -> EventBuilder.withBody(String.valueOf(user), Charsets.UTF_8))
                    .collect(Collectors.toList());
            getChannelProcessor().processEventBatch(events);

            status = Status.READY;
        } catch (Throwable t) {
            // Log exception, handle individual exceptions as needed

            status = Status.BACKOFF;

            // re-throw all Errors
            if (t instanceof Error) {
                throw (Error)t;
            }
        }
        return status;
    }

    @Override
    public long getBackOffSleepIncrement() {
        System.out.println("Printing getBackOffSleepIncrement | " + backoffSleepIncrement);
        return backoffSleepIncrement;
    }

    @Override
    public long getMaxBackOffSleepInterval() {
        System.out.println("Printing getMaxBackOffSleepInterval | " + maxBackOffSleepInterval);
        return maxBackOffSleepInterval;
    }
};



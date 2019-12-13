package io.turntabl;

import com.google.common.base.Charsets;
import org.apache.flume.*;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.event.EventBuilder;
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



public class Main extends AbstractSource
        implements EventDrivenSource, Configurable {

    MyTwitterController myTwitterController = null;

    public void configure(Context context) {
         myTwitterController = new MyTwitterController(context);
    }

    public void start() {
        // The channel is the piece of Flume that sits between the Source and Sink,
        // and is used to process events.
        final ChannelProcessor channel = getChannelProcessor();


        List<twitter4j.Status> allTweets = myTwitterController.fetchTweets();
        List<User> finalLIst = allTweets.stream()
                .map(user -> user.getUser())
                .collect(Collectors.toList());
        List<Event> events = finalLIst.stream()
                .map(user -> EventBuilder.withBody(String.valueOf(user), Charsets.UTF_8))
                .collect(Collectors.toList());
        channel.processEventBatch(events);


    }

    public void stop() {

        super.stop();
    }

}


//public class Main extends AbstractSource
//        implements PollableSource, Configurable {
//
//    MyTwitterController myTwitterController = null;
//
//    @Override
//    public void configure(Context context) {
//        MyTwitterController myTwitterController = new MyTwitterController(context);
//    }
//
//    @Override
//    public void start() {
//        // Initialize the connection to the external client
//    }
//
//    @Override
//    public void stop () {
//        // Disconnect from external client and do any additional cleanup
//        // (e.g. releasing resources or nulling-out field values) ..
//        super.stop();
//    }
//
//
//    @Override
//    public Status process() throws EventDeliveryException {
//
//        Status status = null;
//
//        try {
//            // This try clause includes whatever Channel/Event operations you want to do
//
//            // Receive new data
//            //Timer timer = new Timer();
//            //TimerTask doAsynchronousTask = new TimerTask() {
//              //  @Override
//                //public void run() {
//
////                    List<twitter4j.Status> allTweets = myTwitterController.fetchTweets();
////                    List<User> finalLIst = allTweets.stream()
////                            .map(status -> status.getUser())
////                            .collect(Collectors.toList());
////                    List<Event> events = finalLIst.stream()
////                            .map(user -> EventBuilder.withBody(String.valueOf(user), Charsets.UTF_8))
////                            .collect(Collectors.toList());
////                    getChannelProcessor().processEventBatch(events);
//
////                System.out.println("Response from AllTweets in Main | " + finalLIst);
////
////                }
////            };
//            //Every 2 mina makes call to fetch data from twitter
//            //timer.schedule(doAsynchronousTask, 0, 120000);
//
//            List<twitter4j.Status> allTweets = myTwitterController.fetchTweets();
//            List<User> finalLIst = allTweets.stream()
//                    .map(user -> user.getUser())
//                    .collect(Collectors.toList());
//            List<Event> events = finalLIst.stream()
//                    .map(user -> EventBuilder.withBody(String.valueOf(user), Charsets.UTF_8))
//                    .collect(Collectors.toList());
//            getChannelProcessor().processEventBatch(events);
//
//
//            status = Status.READY;
//        } catch (Throwable t) {
//            // Log exception, handle individual exceptions as needed
//
//            status = Status.BACKOFF;
//
//            // re-throw all Errors
//            if (t instanceof Error) {
//                throw (Error)t;
//            }
//        }
//        return status;
//    }
//
//    @Override
//    public long getBackOffSleepIncrement() {
//        return 0;
//    }
//
//    @Override
//    public long getMaxBackOffSleepInterval() {
//        return 0;
//    }
//};



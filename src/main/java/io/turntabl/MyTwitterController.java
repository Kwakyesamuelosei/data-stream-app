package io.turntabl;

import java.util.*;


import twitter4j.conf.ConfigurationBuilder;
import twitter4j.*;
import java.util.*;

import org.apache.flume.*;


public class MyTwitterController {


    MyTwitterController() {}


    public List<JSONObject> fetchTweets() {
        List<JSONObject>allTweets = null;

        try {

            allTweets = this.dummyData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTweets;
    }

    public List<JSONObject> dummyData(){

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("id",11833693);
        jsonObject1.put("name","papa quecy");
        jsonObject1.put("screenName","QuecyPapa");
        jsonObject1.put("location","Koforidua, Ghana");
        jsonObject1.put("reTweetCount",5);
        jsonObject1.put("followersCount",6);
        jsonObject1.put("isFollowRequestSent",false);
        jsonObject1.put("profileImageUrl","http://pbs.twimg.com/profile_images/1184539643464900610/5buOSA1L_normal.jpg");
        jsonObject1.put("isFollowRequestSent",false);
        jsonObject1.put("isVerified",false);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("id",11453693);
        jsonObject2.put("name","Nana");
        jsonObject2.put("screenName","Nana25893087");
        jsonObject2.put("location","Kumasi, Ghana");
        jsonObject2.put("reTweetCount",10);
        jsonObject2.put("followersCount",30);
        jsonObject2.put("isFollowRequestSent",false);
        jsonObject2.put("profileImageUrl","http://pbs.twimg.com/profile_images/1202705788759883778/K0rb00B__normal.jpg");
        jsonObject2.put("isFollowRequestSent",false);
        jsonObject2.put("isVerified",false);

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("id",11476693);
        jsonObject3.put("name","Agba Awo");
        jsonObject3.put("screenName","Patoolee2");
        jsonObject3.put("location","Accra, Ghana");
        jsonObject3.put("reTweetCount",12);
        jsonObject3.put("followersCount",150);
        jsonObject3.put("isFollowRequestSent",false);
        jsonObject3.put("profileImageUrl","http://pbs.twimg.com/profile_images/1174499821463461889/JY0WfvAH_normal.jpg");
        jsonObject3.put("isFollowRequestSent",false);
        jsonObject3.put("isVerified",true);


        List data = Arrays.asList(
                jsonObject1,
                jsonObject1,
                jsonObject1,
                jsonObject1,
                jsonObject1,
                jsonObject2,
                jsonObject2,
                jsonObject2,
                jsonObject2,
                jsonObject2,
                jsonObject3,
                jsonObject3,
                jsonObject3,
                jsonObject3
        );
        return data;
    }

}



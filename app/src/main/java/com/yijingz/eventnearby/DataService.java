package com.yijingz.eventnearby;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataService {
    public static List<Event> getEventData() {
        List<Event> eventData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            eventData.add(new Event("Event_name",
                    "330 De Neve Drive",
                    "My dorm oh boi.", randomImageUrl()

            ));
        }
        return eventData;
    }

    public static String randomImageUrl() {
        String[] urlList = new String[] {
                "https://i.imgur.com/Ii177wV.jpg?1",
                "https://i.imgur.com/uF2AcYk.jpg",
                "https://i.imgur.com/HNzCNZA.png",
                "http://i.imgur.com/DvpvklR.png"
        };

        Random rand = new Random();
        int pos = rand.nextInt(4);
        return urlList[pos];

    }
}

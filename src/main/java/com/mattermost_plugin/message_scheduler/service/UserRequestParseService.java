package com.mattermost_plugin.message_scheduler.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.mattermost_plugin.message_scheduler.model.ScheduledMessage;
import com.mattermost_plugin.message_scheduler.model.TimeRecord;

import reactor.core.publisher.Mono;

@Service
public class UserRequestParseService {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    public LocalDateTime extractMMDDHHMMFromMessage(String text) {
        LocalDateTime today = LocalDateTime.now();
        Integer year = today.getYear();
        TimeRecord scheduleTime;

        String[] timeContentArr = text.split(" ", 2);
        String[] timeTextArr = timeContentArr[0].split("[/:-]");
        if (timeTextArr.length <= 3 || timeTextArr.length > 5) {  // MM_DD_hh_mm or YY_MM_DD_hh_mm
            return null;
        }

        // Validate Using TimeRecord
        if (timeTextArr.length == 5) {
            scheduleTime = new TimeRecord(timeTextArr[0], timeTextArr[1], timeTextArr[2], timeTextArr[3], timeTextArr[4]);
        } else {
            scheduleTime = new TimeRecord(year.toString(), timeTextArr[0], timeTextArr[1], timeTextArr[2], timeTextArr[3]);
        }

        return LocalDateTime.parse(scheduleTime.year() + scheduleTime.month() + scheduleTime.day() + scheduleTime.hour() + scheduleTime.minute(), dateTimeFormatter);
    }

    public String extractScheduledMessage(String text) {

        String[] timeContentArr = text.split(" ", 2);
        return timeContentArr[1];
    }

    public Mono<ScheduledMessage> parseMap2ScheduledMessage(Mono<MultiValueMap<String, String>> userInputMap) {
        return userInputMap.flatMap(map -> {
            var input = map.toSingleValueMap();
            String userName = input.get("user_name");
            String channelName = input.get("channel_name");
            String message = input.get("text");
            ScheduledMessage scheduledMessage = new ScheduledMessage(channelName, userName,
                                                                     extractMMDDHHMMFromMessage(message),
                                                                     extractScheduledMessage(message));
            return Mono.just(scheduledMessage);
        });
    }
}

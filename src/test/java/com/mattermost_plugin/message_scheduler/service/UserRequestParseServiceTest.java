package com.mattermost_plugin.message_scheduler.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;


class UserRequestParseServiceTest {

    private final UserRequestParseService userRequestParseService = new UserRequestParseService();
    @Test
    void testExtractMMDDHHMMFromMessage(){
//        String text = "23/03/21/10";
        String text = "23-03-21-10";
        List<String> li = Arrays.stream(text.split("[/-]")).toList();
        System.out.println(li);
        System.out.println(Integer.parseInt("01"));
        LocalDateTime time;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        time = LocalDateTime.parse("202301192401",dateTimeFormatter);
        System.out.println(time);
    }
}
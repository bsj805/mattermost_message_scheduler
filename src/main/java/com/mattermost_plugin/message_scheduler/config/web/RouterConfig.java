package com.mattermost_plugin.message_scheduler.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mattermost_plugin.message_scheduler.endpoints.ScheduleHandler;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> mainRouter(ScheduleHandler scheduleHandler){
        return RouterFunctions.route()
                .GET("/", scheduleHandler::healthCheck)
                .POST("/schedule", scheduleHandler::scheduleMessage)
                .build();
    }
}

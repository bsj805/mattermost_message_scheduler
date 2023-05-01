package com.mattermost_plugin.message_scheduler.endpoints;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mattermost_plugin.message_scheduler.model.ScheduledMessage;
import com.mattermost_plugin.message_scheduler.service.UserRequestParseService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ScheduleHandler {

    private final UserRequestParseService userRequestParseService;
    // health check endpoint
    public Mono<ServerResponse> healthCheck(ServerRequest request){
        return ServerResponse.ok().bodyValue("OK");
    }

    // receive x-www-form-urlencoded `POST` request
    public Mono<ServerResponse> scheduleMessage(ServerRequest request){
        Mono<MultiValueMap<String, String>> formData = request.formData();
        Mono<ScheduledMessage> message = userRequestParseService.parseMap2ScheduledMessage(formData);
        System.out.println(formData);
        return ServerResponse.ok().bodyValue("OK");
    }
}

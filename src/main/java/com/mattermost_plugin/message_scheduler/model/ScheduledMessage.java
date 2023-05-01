package com.mattermost_plugin.message_scheduler.model;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("scheduledMessage")
public class ScheduledMessage {
    @Id
    private ObjectId id;

    private String channelName;
    private String userName;
    private LocalDateTime scheduledDate;
    @CreatedDate
    private LocalDateTime registerDate;
    private String jsonMessage;

    public ScheduledMessage(String channelName, String userName, LocalDateTime scheduledDate, String jsonMessage){
        this.channelName = channelName;
        this.userName = userName;
        this.scheduledDate = scheduledDate;
        this.jsonMessage = jsonMessage;
    }
}

package com.itsol.recruit.web.rest;

import com.itsol.recruit.dto.Hello;
import com.itsol.recruit.dto.User;
import com.itsol.recruit.entity.Notifications;
import com.itsol.recruit.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {
    @Autowired
    private NotificationsService notificationsService;

    @MessageMapping("/hello")
    @SendTo("/topic/hi")
    public Hello greeting(User user) throws Exception {
        return new Hello("Hi, " + user.getName() + "!");
    }

    @MessageMapping("/job-register")
    @SendTo("/topic/apply")
    public Notifications applyOnJob(Notifications notifications) throws Exception {
        return notificationsService.add(notifications);
    }

}

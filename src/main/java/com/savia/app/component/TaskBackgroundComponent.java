package com.savia.app.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskBackgroundComponent {

    @Scheduled(fixedDelay = -1,cron = "-")
    public void test(){
        System.out.println("Hola Angel");
    }
}

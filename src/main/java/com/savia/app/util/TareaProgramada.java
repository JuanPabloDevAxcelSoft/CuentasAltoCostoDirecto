package com.savia.app.util;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@Component
public class TareaProgramada implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Timer timer= new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                System.out.println("Tarea programada");
            }
        };
        timer.scheduleAtFixedRate(timerTask,15000,15000);
    }
}

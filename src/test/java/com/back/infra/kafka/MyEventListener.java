package com.back.infra.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MyEventListener { // 이벤트를 받는 역할

    private final CountDownLatch latch = new CountDownLatch(1);
    private MyEvent receivedEvent;

    // 컨슈머: 이벤트를 수신하는 주체
    @KafkaListener(topics = "MyEvent") // 객체 이름을 기반으로 받음
    public void handle(MyEvent event) {
        System.out.println("handle MyEvent: " + event.msg());
        this.receivedEvent = event;
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public MyEvent getReceivedEvent() {
        return receivedEvent;
    }
}
package dev.be.async.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/*
 * 비동기 처리는 Spring Framework가 비동기로 처리하고자 하는 메소드를 비동기 처리를 위해 한번 더 Wrapping한다.
 * 정확히 말하면, 메소드를 포함하는 EmailService를 순수한 Bean 형태로 갖고오는 것이 아니라,
 * 한번 더 Wrapping한 Bean으로 등록된 EmailService를 갖고 온다.
 * 즉, 비동기 처리를 위해서는 Spring 컨테이너에 등록되어 있는 Bean을 사용해야한다.
 */
@Service
@RequiredArgsConstructor
public class AsyncService {

    private final EmailService emailService;

    public void asyncCall_1() {
        /* 위 예제의 경우 셋 다 서로 다른 Thread 에서 실행 -> 비동기 성공 */
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        emailService.sendMail();    //Sub Thread에게 위임하여 비동기 방식으로 처리
        emailService.sendMailWithCustomThreadPool();    ////Sub Thread에게 위임하여 비동기 방식으로 처리
    }

    public void asyncCall_2() {
        /* 위 예제의 경우 셋 다 같은 Thread 에서 실행 -> 비동기 실패 */
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService = new EmailService();
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    public void asyncCall_3() {
        /**
         * 위 예제의 경우 둘 다 같은 Thread 에서 실행 -> 비동기 실패
         * sendMail()은 Wrapping된 Bean이 아님.
         */
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();
    }

    @Async
    public void sendMail() {
        System.out.println("[sendMail] :: " + Thread.currentThread().getName());
    }

}

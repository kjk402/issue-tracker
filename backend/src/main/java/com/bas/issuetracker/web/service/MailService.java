package com.bas.issuetracker.web.service;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@EnableAutoConfiguration
@PropertySource(value = "classpath:google-mail.properties")
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    private JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String FROM_ADDRESS;

    public void mailSend(String toAddress, String nickname) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                String welcome = String.format("<div>%s 님 안녕하세요.", nickname);
                try {
                    MailHandler mailHandler = new MailHandler(mailSender);

                    // 받는 사람
                    mailHandler.setTo(toAddress);
                    // 보내는 사람
                    mailHandler.setFrom(FROM_ADDRESS);
                    // 제목
                    mailHandler.setSubject("이슈 트래커 API 가입 축하 메일입니다.");
                    // HTML Layout

                    mailHandler.setText("<html>"
                            + "<body>"
                            + welcome
                            + "<div><strong>Issue Tracker API 가입을 환영합니다.</strong></div>"
                            + "<div>"
                            + "<img src='cid:welcome' style='float:center;width:550px;height:180px;'/>"
                            + "</div>"
                            + "<div></div>" + "<div>"
                            + "<a href=http://ec2-13-124-158-166.ap-northeast-2.compute.amazonaws.com/api/swagger-ui.html#/><img src='cid:swagger' style='float:center;width:120px;height:50px;'/></a>"
                            + "<a href=https://github.com/kjk402/issue-tracker><img src='cid:github' style='float:center;width:120px;height:50px;'/></a>"
                            + "</div>"
                            + "<div style='font-style: italic ; \n" +
                            "font-weight: bold; \n" +
                            "font-size: 3.5em;\n" +
                            "line-height: 1.0em; \n" +
                            "color: navy;\n" +
                            "font-family: arial;'>Thanks</div>"
                            + "<div style='height: auto; width: 200px; border:5px solid #4a4a4f; border-radius: 5px; background-color: #d5d7e0;'>API made by Bat & Nas<br>문의 메일 주소<br>Bat: joonki402@gmail.com<br>Nas: scra1028@gmail.com</div>"
                            + "</div></body>"
                            + "</html>", true);
                    // 첨부 파일
//            mailHandler.setAttach("stars", "static/stars.jpg");
                    // 이미지 삽입
                    mailHandler.setInline("welcome", "/static/welcome.jpg");
                    mailHandler.setInline("swagger", "/static/swagger.jpg");
                    mailHandler.setInline("github", "/static/github.jpg");

                    logger.info("{} 님에게 가입메일 전송 시작", nickname);
                    mailHandler.send();
                    logger.info("{} 님에게 가입메일 전송 완료", nickname);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(task).start();
    }
}

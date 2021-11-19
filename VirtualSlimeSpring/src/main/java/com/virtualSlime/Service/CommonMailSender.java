package com.virtualSlime.Service;

import com.virtualSlime.Entity.User;
import com.virtualSlime.Utils.UserVerificationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.yml")
public class CommonMailSender {
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Async
    public void sendCertificationCode(UserVerificationWrapper wrapper){
        //this function is slow, thus set to async
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(wrapper.getUser().getUserEmail());
        message.setSubject("您的VirtualSlime账户激活码");
        message.setText("为了您能正常使用VirtualSlime，\n"
                + "请在5分钟内为用户\"" + wrapper.getUser().getUserName()
                + "\"使用以下代码\n"
                + wrapper.getCode());
        javaMailSender.send(message);
    }
}

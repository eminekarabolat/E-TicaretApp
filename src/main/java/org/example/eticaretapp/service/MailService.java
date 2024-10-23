package org.example.eticaretapp.service;

import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.entity.Mail;
import org.example.eticaretapp.entity.User;
import org.example.eticaretapp.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailRepository mailRepository;



    private final JavaMailSender mailSender;
    private final String fromAddress = "msacakk@gmail.com";
    private final String url = "www.bombabomba.com";

    public SimpleMailMessage makeMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromAddress);
        return simpleMailMessage;
    }

    public void sendVerificaitonMail(User user){
        String randomNum = UUID.randomUUID().toString();
        SimpleMailMessage smm = makeMessage();
        smm.setTo(user.getEmail());
        smm.setSubject("Üyelik doğrulama hk.");
        smm.setText("Üyeliğinizi doğrulamak için kodunuz: "+randomNum);
        try{
            Mail mail = Mail.builder().authId(user.getAuthId()).activationCode(randomNum).build();
            mailRepository.save(mail);
            mailSender.send(smm);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public List<Mail> findAll() {
        return mailRepository.findAll();
    }
}

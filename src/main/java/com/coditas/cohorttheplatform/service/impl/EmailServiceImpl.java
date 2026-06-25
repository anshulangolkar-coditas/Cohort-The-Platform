package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.exception.EmailSendingFailureException;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String inviteUser(String emailId){

        String uniqueKey = null;

        String invitationMessage = """
                We Invite you to register for the company,
                find the below registration key.
                Please register before the invitation expires.
                Unique Key:
                """;

        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            uniqueKey = UUID.randomUUID().toString();

            mailMessage.setFrom(sender);
            mailMessage.setTo(emailId);
            mailMessage.setSubject("Invitation for Employee On-Board");
            mailMessage.setText(invitationMessage+" "+uniqueKey);

            javaMailSender.send(mailMessage);

        }catch (EmailSendingFailureException ex){
            throw new EmailSendingFailureException(ExceptionMessages.EMAIL_SENDING_FAILURE);
        }
        return uniqueKey;
    }

    @Override
    public void materialUploadEmail(List<String> emailList){

        if(emailList.isEmpty()){
            return;
        }

/*        String[] emailIds = new String[emailList.size()];
        emailIds = emailList.toArray(emailIds);*/

        String[] emailIds = emailList.toArray(new String[0]);

        String materialUploadMessage = """
                New material has been uploaded. Please go the dashboard and check it out!!!
                """;

        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setBcc(emailIds);
            mailMessage.setSubject("New Material Uploaded!!!");
            mailMessage.setText(materialUploadMessage);

            javaMailSender.send(mailMessage);

        }catch (EmailSendingFailureException ex){
            throw new EmailSendingFailureException(ExceptionMessages.EMAIL_SENDING_FAILURE);
        }

    }

}

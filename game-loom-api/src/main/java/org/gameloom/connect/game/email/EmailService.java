package org.gameloom.connect.game.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    @Async
    public void sendEmail(
            String to,
            String username,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String activationCode,
             String subject

    ) throws MessagingException {
        String templateName;
        if (emailTemplate == null) {
            templateName = "confirm-email";
        }else{
            templateName = emailTemplate.name();
        }
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
                );
        Map<String, Object> propreties = new HashMap<>();

        propreties.put("username", username);
        propreties.put("activationCode", activationCode);
        propreties.put("confirmationUrl", confirmationUrl);
        Context context = new Context();
        context.setVariables(propreties);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setFrom("lemidanibassem@gmail.com");
        mimeMessageHelper.setSubject(subject);
        String html = templateEngine.process(templateName, context);
        mimeMessageHelper.setText(html, true);
        mailSender.send(mimeMessage);





    }
}

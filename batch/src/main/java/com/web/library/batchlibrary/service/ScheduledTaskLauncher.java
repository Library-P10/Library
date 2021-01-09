package com.web.library.batchlibrary.service;

import com.web.library.batchlibrary.model.LoginBean;
import com.web.library.batchlibrary.model.WaitingList;
import com.web.library.batchlibrary.proxy.FeignProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class ScheduledTaskLauncher {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskLauncher.class);

    @Autowired
    private FeignProxy feignProxy;

    @Autowired
    private MailService mailService;

//    cron="0 0 1 * * ?"
//    config cron pour production ( check 1 fois par jour )
    @Scheduled(cron = "*/30 * * * * ?")
    public void runScheduledTask(){

        /**
         * Envoie de mail de relance
         */
        ResponseEntity<?> testToken = feignProxy.validationAuthentication(new LoginBean());

        Cookie cookie = new Cookie("Token", ((Map<String,String>) testToken.getBody()).get("token"));
        String token = "Bearer "+ cookie.getValue();

        mailService.sendMailReturnBook(token);

        // Mail pour la récupération d'un exemplaire
        mailService.sendMailGetBook(token);

        logger.info("Mail envoyé");

        /**
         * Vérification des récupération des livres dans la waitingList (48h)
         */

        // On récupère toutes les réservations en attente dont le livres n'a pas été récupérer
        // dans les 48h ( Get dateRecoveryLimit )
        List<WaitingList> waitingLists = feignProxy.getWaitingListByDateRecoveryLimitExceeded(token);
        feignProxy.changerCustomerInWaitingList(token, waitingLists);

        logger.info("Mail envoyé au suivant");
    }
}

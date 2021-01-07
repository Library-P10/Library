package com.api.library.service.impl;

import com.api.library.dto.WaitingListDto;
import com.api.library.mapper.EmpruntMapper;
import com.api.library.mapper.WaitingListMapper;
import com.api.library.model.Copy;
import com.api.library.model.WaitingList;
import com.api.library.repository.*;
import com.api.library.service.contract.MailService;
import com.api.library.service.contract.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class WaitingListServiceImpl implements WaitingListService {

    // ----------------- Injections des dépendances ----------------- //

    private final WaitingListRepository waitingListRepository;
    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;
    private final MailService mailService;

    @Autowired
    public WaitingListServiceImpl(WaitingListRepository waitingListRepository,
                                  BookRepository bookRepository,
                                  CopyRepository copyRepository,
                                  MailService mailService){
        this.waitingListRepository = waitingListRepository;
        this.bookRepository = bookRepository;
        this.copyRepository = copyRepository;
        this.mailService = mailService;
    }

    // -----------------------------------------------------  //

    @Override
    public List<WaitingListDto> getWaitingListByIdBook(final Long idBook) {
        return WaitingListMapper.
                INSTANCE.map(waitingListRepository.getWaitingListByIdBook(idBook));
    }

    @Override
    public Integer getNumberBookInWaitingList(final Long idBook) {
        return waitingListRepository.getNumberBookInWaitingList(idBook);
    }

    /**
     * Supprime la réservation de la liste d'attente
     * @param idWaitingList
     */
    @Override
    public void deleteWaitingList(final Long idWaitingList) {
        WaitingList waitingList = waitingListRepository.getWaitingListById(idWaitingList);
        waitingListRepository.delete(waitingList);
    }

    /**
     * Envoi un mail au prochain sur la liste d'attente
     * @param idBook
     */
    @Override
    public void sendMailForNextCustomer(final Long idBook) {
        String status = "En attente de récupération";

        Copy copy =copyRepository.getCopyByStatus(idBook, status);

        if (waitingListRepository.getWaitingListByIdBook(idBook) == null){
            copyRepository.updateStatusAvailable(copy.getId());
        }else {
            copyRepository.updateStatusWaitingList(copy.getId());

            Calendar calendar = Calendar.getInstance();
            Date date = new Date();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 48);
            Date dateRecoveryLimit = calendar.getTime();


            // Récupération du premier à être sur la liste d'attente
            WaitingList waitingList =waitingListRepository.findFirstByIdBook(idBook);
            // Change les données du premier à être sur la liste d'attente
            waitingListRepository.updateWaitingListAfterSendMail(waitingList.getId(), date, dateRecoveryLimit);

            mailService.sendMessage(waitingList.getCustomer().getEmail(),waitingList.getCustomer().getFirstName(),
                    waitingList.getCustomer().getLastName(), copy.getBook().getTitle());
        }
    }

    /**
     * Récupère la waitingList par son id
     * @param idWaiting
     * @return
     */
    @Override
    public WaitingListDto getWaitingListById(final Long idWaiting) {
        WaitingList waitingList = waitingListRepository.getWaitingListById(idWaiting);
        return WaitingListMapper.INSTANCE.waitingListToWaitingListDto(waitingList);
    }

}

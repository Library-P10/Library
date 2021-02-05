package com.api.library.service.impl;

import com.api.library.dto.EmpruntDto;
import com.api.library.dto.WaitingListDto;
import com.api.library.mapper.BookMapper;
import com.api.library.mapper.CustomerMapper;
import com.api.library.mapper.EmpruntMapper;
import com.api.library.mapper.WaitingListMapper;
import com.api.library.model.Copy;
import com.api.library.model.WaitingList;
import com.api.library.repository.*;
import com.api.library.service.contract.MailService;
import com.api.library.service.contract.WaitingListService;
import com.api.library.service.exception.EmpruntNotFoundException;
import com.api.library.service.exception.WaitingListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class WaitingListServiceImpl implements WaitingListService {

    // ----------------- Injections des dépendances ----------------- //

    private final WaitingListRepository waitingListRepository;
    private final EmpruntRepository empruntRepository;
    private final CopyRepository copyRepository;
    private final MailService mailService;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public WaitingListServiceImpl(WaitingListRepository waitingListRepository,
                                  EmpruntRepository empruntRepository,
                                  CopyRepository copyRepository,
                                  MailService mailService,
                                  BookRepository bookRepository,
                                  CustomerRepository customerRepository){
        this.waitingListRepository = waitingListRepository;
        this.empruntRepository = empruntRepository;
        this.copyRepository = copyRepository;
        this.mailService = mailService;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    // -----------------------------------------------------  //

    @Override
    public List<WaitingListDto> getWaitingListByIdBookByDateRequest(final Long idBook) {
        return WaitingListMapper.
                INSTANCE.map(waitingListRepository.getWaitingListByIdBookByDateRequest(idBook));
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
        String status = "Waiting";

        Copy copy =copyRepository.getCopyByStatus(idBook, status);
        List<WaitingList> waitingLists = waitingListRepository.getWaitingListByIdBookByDateRequest(idBook);

        if (waitingLists == null){
            copyRepository.updateStatusAvailable(copy.getId());
        }else {
            copyRepository.updateStatusWaitingList(copy.getId());

            Calendar calendar = Calendar.getInstance();
            Date date = new Date();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 48);
            Date dateRecoveryLimit = calendar.getTime();


            // Récupération du premier à être sur la liste d'attente
            WaitingList waitingList = waitingLists.get(0);
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

    /**
     * Récupère les réservations dont la dateRecoveryLimit est dépassée
     * @return
     */
    @Override
    public List<WaitingListDto> getWaitingListByDateRecoveryLimitExceeded() {
        return WaitingListMapper.INSTANCE.map(waitingListRepository.getWaitingListByDateRecoveryLimitExceeded());
    }

    @Override
    public Boolean insertInWaitingListAvailable(int numberBookInWaitingList,
                                                int numberOfWaitinListAvailable,
                                                Long idBook, Long idCustomer) {

        // On multiplie par 2 le nombre de réservation possible selon le nombre d'exemplaire
        numberOfWaitinListAvailable = numberOfWaitinListAvailable * 2;

        // Si le nombre de réservation en attente est égale ou supérieur au nombre de réservation possible
        // ou si l'utilisateur à déjà réservé ou à déjà le livre en emprunt
        // Return false
        // Sinon
        // Return true

        // Récupération de la réservation en attente si elle existe selon idBook et idCustomer
        WaitingListDto waitingListDto = WaitingListMapper.INSTANCE.waitingListToWaitingListDto(
                waitingListRepository.getWaitingListByIdCustomerAndIdBook(idCustomer, idBook));

        // Récupération de l'emprunt si elle existe selon idBook et idCustomer
        EmpruntDto empruntDto = EmpruntMapper.INSTANCE.empruntToEmpruntDto(
                empruntRepository.getEmpruntByIdCustomerByIdBook(idCustomer, idBook));

        // Récupération du nombre de Copy disponible
        int numberOfCopyAvailable = copyRepository.getNumberCopyByBookAvailable(idBook);

        if ( (waitingListDto !=null) || (empruntDto != null)
                || (numberOfWaitinListAvailable == numberBookInWaitingList
                || (numberOfCopyAvailable != 0))) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Récupération d'une liste d'attente selon l'utilisateur
     * @param idCustomer
     * @return
     */
    @Override
    public WaitingListDto getWaitingListByIdCustomerAndIdBook(Long idCustomer, Long idBook) {
        return WaitingListMapper.INSTANCE.waitingListToWaitingListDto
                (waitingListRepository.getWaitingListByIdCustomerAndIdBook(idCustomer, idBook));
    }

    /**
     * Ajout d'une réservation dans la liste d'attente
     * @param idBook
     * @param idCustomer
     * @return
     */
    @Override
    public WaitingListDto insertWaitingList(final Long idBook, final Long idCustomer) {

        int numberOfWaitinListAvailable;
        int numberBookInWaitingList;

        // Récupération du nombre d'exemplaires de l'ouvrage
        numberOfWaitinListAvailable = copyRepository.getNumberCopyByBook(idBook);
        // Récupération du nombre de réservation en attente
        numberBookInWaitingList = waitingListRepository.getNumberBookInWaitingList(idBook);
        if (!insertInWaitingListAvailable(numberBookInWaitingList, numberOfWaitinListAvailable, idBook, idCustomer)){
            throw new WaitingListException("WaitingList not available");
        }

        WaitingListDto waitingListDto = new WaitingListDto();
        Date date = new Date();

        waitingListDto.setBook(BookMapper.INSTANCE.bookToBookDto(bookRepository.getBookById(idBook)));
        waitingListDto.setCustomer(CustomerMapper.INSTANCE.customerToCustomerDto(
                customerRepository.findCustomerById(idCustomer)));
        waitingListDto.setDateRequest(date);

        waitingListRepository.save(WaitingListMapper.INSTANCE.waitingListDtoToWaitingList(waitingListDto));

        return waitingListDto;
    }

    @Override
    public int getNumberCustomerInWaitingList(final Long idBook) {
        return waitingListRepository.getNumberCustomerInWaitingList(idBook);
    }

    /**
     * Récupération de la liste d'attente de l'utilisateur
     * @param idCustomer
     * @return
     */
    @Override
    public List<WaitingListDto> getWaitingListByIdCustomer(final Long idCustomer) {
        return WaitingListMapper.INSTANCE.map(waitingListRepository.getWaitingListByIdCustomer(idCustomer));
    }

    /**
     * Récupère la position de l'utilisateur selon sa dateRequest
     * @param waitingListDto
     * @return
     */
    @Override
    public int getInWaitingList(WaitingListDto waitingListDto) {

        int number = waitingListRepository.getNumberInWaitingList(waitingListDto.getBook().getId(),
                waitingListDto.getDateRequest());

        number = number + 1;

        return number;
    }

    /**
     *
     * @param numberInWaitingList
     * @param returnDate
     * @param waitingListDto
     */
    @Override
    public void updateWaintingList(final int numberInWaitingList, final Date returnDate, WaitingListDto waitingListDto) {
        waitingListDto.setNumberInWaitingList(numberInWaitingList);
        waitingListDto.setNextReturn(returnDate);
    }

}

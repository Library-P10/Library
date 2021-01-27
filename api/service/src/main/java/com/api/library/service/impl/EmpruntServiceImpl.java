package com.api.library.service.impl;

import com.api.library.dto.BookDto;
import com.api.library.dto.CopyDto;
import com.api.library.dto.CustomerDto;
import com.api.library.dto.EmpruntDto;
import com.api.library.mapper.BookMapper;
import com.api.library.mapper.CopyMapper;
import com.api.library.mapper.CustomerMapper;
import com.api.library.mapper.EmpruntMapper;
import com.api.library.model.Emprunt;
import com.api.library.model.WaitingList;
import com.api.library.repository.CopyRepository;
import com.api.library.repository.CustomerRepository;
import com.api.library.repository.EmpruntRepository;
import com.api.library.repository.WaitingListRepository;
import com.api.library.service.contract.EmpruntService;
import com.api.library.service.contract.MailService;
import com.api.library.service.exception.EmpruntNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EmpruntServiceImpl implements EmpruntService {

    // ----------------- Injections de dépendances ----------------- //

    private final WaitingListRepository waitingListRepository;
    private final MailService mailService;
    private final EmpruntRepository empruntRepository;
    private final CopyRepository copyRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public EmpruntServiceImpl (WaitingListRepository waitingListRepository,
                               MailService mailService,
                               EmpruntRepository empruntRepository,
                               CopyRepository copyRepository,
                               CustomerRepository customerRepository){
        this.waitingListRepository = waitingListRepository;
        this.mailService = mailService;
        this.empruntRepository = empruntRepository;
        this.copyRepository = copyRepository;
        this.customerRepository = customerRepository;
    }

    // -----------------------------------------------------  //

    /**
     * Récupère les prêts de l'utilisateur selon son id
     * @param id
     * @return
     */
    @Override
    public List<EmpruntDto> getEmpruntByIdCustomer(final Long id) {

        List<Emprunt> emprunts = empruntRepository.getEmpruntByIdCustomer(id);

        return EmpruntMapper.INSTANCE.map(emprunts);
    }

    @Override
    public EmpruntDto addEmprunt(Long idBook, String format, String nameLibrary, Long idCustomer) {

        EmpruntDto empruntDto = new EmpruntDto();

        // Récupère une copy selon le format et la library
        CopyDto copyDto = CopyMapper.INSTANCE.copyToCopyDto(copyRepository.findFirstByFormatAndLibrary_Nom(format, nameLibrary, idBook));

        CustomerDto customerDto = CustomerMapper.INSTANCE.customerToCustomerDto(customerRepository.findCustomerById(idCustomer));

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 28);
        Date dateReturn = calendar.getTime();

        empruntDto.setCopy(copyDto);
        empruntDto.setCustomer(customerDto);
        empruntDto.setEmpruntDate(date);
        empruntDto.setExtended(false);
        empruntDto.setReturnDate(dateReturn);

        copyRepository.updateStatusUnavailable(copyDto.getId());

        empruntRepository.save(EmpruntMapper.INSTANCE.empruntDtoToEmprunt(empruntDto));

        return empruntDto;
    }

    /**
     * Supprime un prêt lors du rendu du livre (remet disponible la copy)
     * @param id
     */
    @Override
    public void deleteEmprunt(final Long id) {

        Emprunt emprunt = empruntRepository.getEmpruntById(id);

        empruntRepository.delete(emprunt);
    }

    /**
     * Retour d'un emprunt
     * @param idEmprunt
     */
    @Override
    public void returnEmprunt(final Long idEmprunt) {
        // 1 - Supprimer le prêt
        // 2 - Changer le statut de l'exemplaire
        // Si -> le livre n'existe pas dans la waitingList, statut = " Disponible"
        // Si -> le livre existe dans la waitingList, statut = "Rendu"
        EmpruntDto empruntDto = EmpruntMapper.INSTANCE.empruntToEmpruntDto(empruntRepository.getEmpruntById(idEmprunt));
        BookDto bookDto = BookMapper.INSTANCE.bookToBookDto(empruntDto.getCopy().getBook());
        Long idBook = bookDto.getId();
        List<WaitingList> waitingLists = waitingListRepository.getWaitingListByIdBookByDateRequest(idBook);


        if (waitingLists == null){
            copyRepository.updateStatusAvailable(empruntDto.getCopy().getId());
        }else {
            copyRepository.updateStatusWaitingList(empruntDto.getCopy().getId());

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
                    waitingList.getCustomer().getLastName(), bookDto.getTitle());
        }
        empruntRepository.delete(EmpruntMapper.INSTANCE.empruntDtoToEmprunt(empruntDto));
    }

    /**
     * Supprime l'emprunt selon son idCopy
     * @param idCopy
     */
    @Override
    public void deleteEmpruntByIdCopy(final Long idCopy) {
        Emprunt emprunt = empruntRepository.getEmpruntByIdCopy(idCopy);
        empruntRepository.delete(emprunt);
    }

    /**
     * Prolonge un prêt
     * @param idEmprunt
     */
    @Override
    public void extendLoan(final Long idEmprunt) {

        EmpruntDto empruntDto = EmpruntMapper.INSTANCE.empruntToEmpruntDto(empruntRepository.getEmpruntById(idEmprunt));
        if (empruntDto.getExtended()){
            throw new EmpruntNotFoundException("Emprunt not found");
        }

        Calendar calendar = Calendar.getInstance();
        Date date = empruntDto.getReturnDate();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,28);
        date = calendar.getTime();

        empruntDto.setReturnDate(date);
        empruntDto.setExtended(true);

        empruntRepository.save(EmpruntMapper.INSTANCE.empruntDtoToEmprunt(empruntDto));
    }

    /**
     * Récupère les prêts expirés
     * @return
     */
    @Override
    public List<EmpruntDto> getEmpruntExpiredLoanDate() {
        return EmpruntMapper.INSTANCE.map(empruntRepository.getEmpruntExpiredLoanDate());
    }

    /**
     * Récupère le prochain emprunt à revenir
     * @param idBook
     * @return
     */
    @Override
    public EmpruntDto getNextReturn(final Long idBook) {
        return EmpruntMapper.INSTANCE.empruntToEmpruntDto(empruntRepository.findFirstByCopy_Book_IdOrderByReturnDateAsc(idBook));
    }
}

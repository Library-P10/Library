package com.api.library.service.impl;

import com.api.library.dto.CopyDto;
import com.api.library.dto.CustomerDto;
import com.api.library.dto.EmpruntDto;
import com.api.library.mapper.CopyMapper;
import com.api.library.mapper.CustomerMapper;
import com.api.library.mapper.EmpruntMapper;
import com.api.library.model.Emprunt;
import com.api.library.repository.CopyRepository;
import com.api.library.repository.CustomerRepository;
import com.api.library.repository.EmpruntRepository;
import com.api.library.service.contract.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EmpruntServiceImpl implements EmpruntService {

    // ----------------- Injections de dépendances ----------------- //
    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private CustomerRepository customerRepository;

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

        copyRepository.updateStatusAvailable(emprunt.getCopy().getId());

        empruntRepository.delete(emprunt);
    }

    /**
     * Prolonge un prêt
     * @param idEmprunt
     */
    @Override
    public void extendLoan(final Long idEmprunt) {
        EmpruntDto empruntDto = EmpruntMapper.INSTANCE.empruntToEmpruntDto(empruntRepository.getEmpruntById(idEmprunt));

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
}

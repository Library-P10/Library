package com.api.library.service.contract;

import com.api.library.dto.CustomerDto;
import com.api.library.dto.EmpruntDto;

import java.util.List;

public interface EmpruntService {

    // Récupère les emprunts par l'id de l'utilisateur
    List<EmpruntDto> getEmpruntByIdCustomer(Long id);

    // Ajoute un emprunt
    EmpruntDto addEmprunt(Long idBook, String format, String nameLibrary, Long idCustomer );

    // Supprime un emprunt
    void deleteEmprunt(Long id);

    // Prolonge un prêt
    void extendLoan(Long idEmprunt);

    //Récupère les prêts expirés
    List<EmpruntDto> getEmpruntExpiredLoanDate();
}

package com.api.library.controller;

import com.api.library.dto.CopyByBookDto;
import com.api.library.dto.CopyDto;
import com.api.library.service.contract.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CopyController {

    // ----------------- Injections de dépendances ----------------- //
    @Autowired
    private CopyService copyService;

    // -----------------------------------------------------  //

    /**
     * Récupération des exemplaires et le nombre disponible
     * @param id
     * @return
     */
    @GetMapping(value = "copies/{idBook}")
    public List<CopyByBookDto> displayCopyByIdBook(@PathVariable("idBook") Long id){
        return copyService.getCopyByIdBook(id);
    }

    /**
     *  Retourne l'exemplaire selon l'id
     * @param id
     * @return
     */
    @GetMapping(value = "copy/{idCopy}")
    public CopyDto displayCopyById(@PathVariable("idCopy")Long id){
        return copyService.getCopyById(id);
    }

    /**
     * Retour d'un exemplaire
     * @param idCopy
     * @return
     */
    @GetMapping(value = "copy/return/{idCopy}")
    public void returnCopy(@PathVariable("idCopy")Long idCopy) {
        // Gestion de retour d'un exemplaire
        // 1) - Envoie d'un mail à la première personne qui est dans la waitingList
        // 2) - Ajout de la date d'envoie de mail dans la waitingList
        // 3) - Changer le statut de la copy en "En attente de récupération"

    }
}

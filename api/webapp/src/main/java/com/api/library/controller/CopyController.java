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

}

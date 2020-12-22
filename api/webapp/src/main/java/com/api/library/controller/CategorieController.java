package com.api.library.controller;

import com.api.library.dto.CategorieDto;
import com.api.library.service.contract.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategorieController {

    // ----------------- Injections de dépendances ----------------- //

    @Autowired
    private CategorieService categorieService;

    // ----- ----- //

    /**
     * Récupération de toutes les catégorie qui existe
     * @return
     */
    @GetMapping(value = "categorie")
    public List<CategorieDto> getCategorie(){
        return categorieService.getCategorie();
    }
}

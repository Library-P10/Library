package com.api.library.service.contract;

import com.api.library.dto.CategorieDto;

import java.util.List;

public interface CategorieService {

    // Récupère toutes les catégories
    List<CategorieDto> getCategorie();
}

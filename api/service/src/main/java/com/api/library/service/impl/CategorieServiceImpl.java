package com.api.library.service.impl;

import com.api.library.dto.CategorieDto;
import com.api.library.mapper.CategorieMapper;
import com.api.library.repository.CategorieRepository;
import com.api.library.service.contract.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    // ----------------- Injections de dépendances ----------------- //

    @Autowired
    private CategorieRepository categorieRepository;

    // ----- ----- //

    @Override
    public List<CategorieDto> getCategorie() {
        return CategorieMapper.INSTANCE.map(categorieRepository.getCategorie());
    }
}

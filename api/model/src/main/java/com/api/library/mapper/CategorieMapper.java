package com.api.library.mapper;

import com.api.library.dto.CategorieDto;
import com.api.library.model.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategorieMapper {

    CategorieMapper INSTANCE = Mappers.getMapper(CategorieMapper.class);

    CategorieDto categorieToCategorieDto (Categorie categorie);
    List<CategorieDto> map(List<Categorie> categories);
    Categorie categorieDtoToCategorie(CategorieDto categorieDto);
}

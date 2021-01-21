package com.api.library.mapper;

import com.api.library.dto.CategorieDto;
import com.api.library.model.Categorie;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-21T23:01:56+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
public class CategorieMapperImpl implements CategorieMapper {

    @Override
    public CategorieDto categorieToCategorieDto(Categorie categorie) {
        if ( categorie == null ) {
            return null;
        }

        CategorieDto categorieDto = new CategorieDto();

        categorieDto.setId( categorie.getId() );
        categorieDto.setLabel( categorie.getLabel() );

        return categorieDto;
    }

    @Override
    public List<CategorieDto> map(List<Categorie> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategorieDto> list = new ArrayList<CategorieDto>( categories.size() );
        for ( Categorie categorie : categories ) {
            list.add( categorieToCategorieDto( categorie ) );
        }

        return list;
    }

    @Override
    public Categorie categorieDtoToCategorie(CategorieDto categorieDto) {
        if ( categorieDto == null ) {
            return null;
        }

        Categorie categorie = new Categorie();

        categorie.setId( categorieDto.getId() );
        categorie.setLabel( categorieDto.getLabel() );

        return categorie;
    }
}

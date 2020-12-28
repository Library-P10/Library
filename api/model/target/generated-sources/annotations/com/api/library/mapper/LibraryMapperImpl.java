package com.api.library.mapper;

import com.api.library.dto.LibraryDto;
import com.api.library.model.Library;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-28T16:01:13+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
public class LibraryMapperImpl implements LibraryMapper {

    @Override
    public LibraryDto libraryToLibraryDto(Library library) {
        if ( library == null ) {
            return null;
        }

        LibraryDto libraryDto = new LibraryDto();

        libraryDto.setId( library.getId() );
        libraryDto.setNom( library.getNom() );
        libraryDto.setAdress( library.getAdress() );
        libraryDto.setPhoneNum( library.getPhoneNum() );
        libraryDto.setEmail( library.getEmail() );

        return libraryDto;
    }
}

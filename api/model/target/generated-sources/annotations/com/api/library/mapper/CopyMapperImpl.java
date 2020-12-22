package com.api.library.mapper;

import com.api.library.dto.CopyDto;
import com.api.library.dto.LibraryDto;
import com.api.library.model.Copy;
import com.api.library.model.Library;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T14:30:28+0100",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 11.0.6 (Ubuntu)"
)
public class CopyMapperImpl implements CopyMapper {

    @Override
    public List<CopyDto> map(List<Copy> copies) {
        if ( copies == null ) {
            return null;
        }

        List<CopyDto> list = new ArrayList<CopyDto>( copies.size() );
        for ( Copy copy : copies ) {
            list.add( copyToCopyDto( copy ) );
        }

        return list;
    }

    @Override
    public CopyDto copyToCopyDto(Copy copy) {
        if ( copy == null ) {
            return null;
        }

        CopyDto copyDto = new CopyDto();

        copyDto.setBook( copy.getBook() );
        copyDto.setId( copy.getId() );
        copyDto.setFormat( copy.getFormat() );
        copyDto.setStatus( copy.getStatus() );
        copyDto.setLibrary( libraryToLibraryDto( copy.getLibrary() ) );

        return copyDto;
    }

    @Override
    public Copy copyDtoToCopy(CopyDto copyDto) {
        if ( copyDto == null ) {
            return null;
        }

        Copy copy = new Copy();

        copy.setStatus( copyDto.getStatus() );
        copy.setId( copyDto.getId() );
        copy.setFormat( copyDto.getFormat() );
        copy.setLibrary( libraryDtoToLibrary( copyDto.getLibrary() ) );
        copy.setBook( copyDto.getBook() );

        return copy;
    }

    protected LibraryDto libraryToLibraryDto(Library library) {
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

    protected Library libraryDtoToLibrary(LibraryDto libraryDto) {
        if ( libraryDto == null ) {
            return null;
        }

        Library library = new Library();

        library.setId( libraryDto.getId() );
        library.setNom( libraryDto.getNom() );
        library.setAdress( libraryDto.getAdress() );
        library.setPhoneNum( libraryDto.getPhoneNum() );
        library.setEmail( libraryDto.getEmail() );

        return library;
    }
}

package com.api.library.service.impl;

import com.api.library.dto.CopyByBookDto;
import com.api.library.dto.CopyDto;
import com.api.library.mapper.BookMapper;
import com.api.library.mapper.CopyMapper;
import com.api.library.mapper.LibraryMapper;
import com.api.library.model.Book;
import com.api.library.model.Library;
import com.api.library.repository.CopyRepository;
import com.api.library.service.contract.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CopyServiceImpl implements CopyService {

    // ----------------- Injections de dépendances ----------------- //
    @Autowired
    private CopyRepository copyRepository;

    // -----------------------------------------------------  //

    // Retourne les exemplaires selon l'id du livre et le nombre de dispo
    @Override
    public List<CopyByBookDto> getCopyByIdBook(final Long id) {

        List<CopyByBookDto> copyByBookDtoList =  new ArrayList<>();
        List<Object[]> copies = copyRepository.getCopyByIdBook(id);

        for (final Object[] copy : copies) {
            Book book = (Book) copy[0];
            Library library = (Library) copy[1];
            String format = (String) copy[2];
            int numberCopy = ((Long) copy[3]).intValue();
            CopyByBookDto copyByBookDto = new CopyByBookDto();
            copyByBookDto.setBook(BookMapper.INSTANCE.bookToBookDto(book));
            copyByBookDto.setLibrary(LibraryMapper.INSTANCE.libraryToLibraryDto(library));
            copyByBookDto.setFormat(format);
            copyByBookDto.setNumberCopy(numberCopy);
            copyByBookDtoList.add(copyByBookDto);
        }

        return copyByBookDtoList;
    }

    // Retourne l'exemplaire selon son id
    @Override
    public CopyDto getCopyById(final Long idCopy) {
        return CopyMapper.INSTANCE.copyToCopyDto(copyRepository.getCopyById(idCopy));
    }
}

package com.api.library.service.contract;

import com.api.library.dto.CopyByBookDto;
import com.api.library.dto.CopyDto;

import java.util.List;

public interface CopyService {

    // Récupère les exemplaires selon l'id du livre
    List<CopyByBookDto> getCopyByIdBook (Long id);
    // Récupère l'exemplaire selon l'id du libre
    CopyDto getCopyById( Long idCopy);

}

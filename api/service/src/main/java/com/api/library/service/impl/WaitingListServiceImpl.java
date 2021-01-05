package com.api.library.service.impl;

import com.api.library.dto.WaitingListDto;
import com.api.library.mapper.WaitingListMapper;
import com.api.library.repository.WaitingListRepository;
import com.api.library.service.contract.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingListServiceImpl implements WaitingListService {

    // ----------------- Injections des dépendances ----------------- //

    private final WaitingListRepository waitingListRepository;

    @Autowired
    public WaitingListServiceImpl(WaitingListRepository waitingListRepository){
        this.waitingListRepository = waitingListRepository;
    }

    // -----------------------------------------------------  //

    @Override
    public List<WaitingListDto> getWaitingListByIdBook(final Long idBook) {
        return WaitingListMapper.INSTANCE.map((waitingListRepository.getWaitingListByIdBook(idBook)));
    }

    @Override
    public Integer getNumberBookInWaitingList(final Long idBook) {
        return waitingListRepository.getNumberBookInWaitingList(idBook);
    }

}

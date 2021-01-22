package com.api.library.mapper;

import com.api.library.dto.WaitingListDto;
import com.api.library.model.WaitingList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WaitingListMapper {

    WaitingListMapper INSTANCE = Mappers.getMapper(WaitingListMapper.class);

    List<WaitingListDto> map (List<WaitingList> waitingLists);

    WaitingListDto waitingListToWaitingListDto (WaitingList waitingList);
    WaitingList waitingListDtoToWaitingList (WaitingListDto waitingListDto);
}

package com.example.presidents.service.president;

import com.example.presidents.model.dto.PresidentDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface PresidentService {

    List<PresidentDto> getAllPresidents() ;

    Page<PresidentDto> getAllPresidentsPaginated(Integer pageNumber, Integer pageSize);

    Set<PresidentDto> findPresidentByName(String name);

    Set<PresidentDto> findPresidentsByPoliticalParty(String party);

    PresidentDto savePresident(PresidentDto presidentDto);

    PresidentDto updatePresident(PresidentDto presidentDto);

    PresidentDto updatePresidentPartial(PresidentDto presidentDto);

    void deletePresidentById(Long id);
}

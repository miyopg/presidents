package com.example.presidents.service.president;

import com.example.presidents.exception.exceptions.EntityNotFoundException;
import com.example.presidents.exception.messages.PresidentsControllerExceptionMessages;
import com.example.presidents.model.dto.PresidentDto;
import com.example.presidents.model.entity.President;
import com.example.presidents.model.mapper.PresidentMapper;
import com.example.presidents.repository.PresidentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class PresidentServiceImpl implements PresidentService{

    private final PresidentsRepository presidentsRepository;

    @Override
    public List<PresidentDto> getAllPresidents() {

        return presidentsRepository.findAll().stream()
                .map(PresidentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PresidentDto> getAllPresidentsPaginated(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return presidentsRepository.findAll(pageable).map(PresidentMapper::toDto);
    }

    @Override
    public Set<PresidentDto> findPresidentByName(String name) {
        Set<President> presidents = presidentsRepository.findPresidentsByName(name);
        if(presidents.isEmpty()) {
            throw new EntityNotFoundException(PresidentsControllerExceptionMessages
                    .ENTITY_FOR_PROVIDED_PARAMETER_NOT_EXISTS.getMessage());
        }
        return presidents.stream()
                .map(PresidentMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public Set<PresidentDto> findPresidentsByPoliticalParty(String party) {
        return presidentsRepository.findPresidentByPoliticalParty(party).stream()
                .map(PresidentMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public PresidentDto savePresident(PresidentDto presidentDto) {
        return PresidentMapper.toDto(presidentsRepository.save(PresidentMapper.toEntity(presidentDto)));
    }

    @Override
    public PresidentDto updatePresident(PresidentDto presidentDto) {
        var president = presidentsRepository.findById(presidentDto.getId());
        if(president.isPresent()) {
            president.map(p -> {
                p.setName(presidentDto.getName());
                p.setSurname(presidentDto.getSurname());
                p.setPoliticalParty(presidentDto.getPoliticalParty());
                p.setTermFrom(presidentDto.getTermFrom());
                p.setTermTo(presidentDto.getTermTo());
                return PresidentMapper.toDto(p);
            });
        } else {
            return PresidentMapper.toDto(presidentsRepository.save(PresidentMapper.toEntity(presidentDto)));
        }
        return presidentDto;
    }

    @Override
    public PresidentDto updatePresidentPartial(PresidentDto presidentDto) {
        return presidentsRepository.findById(presidentDto.getId()).map(president -> {
            if (Objects.nonNull(presidentDto.getName())) {
                president.setName(presidentDto.getName());
            }
            if (Objects.nonNull(presidentDto.getSurname())) {
                president.setSurname(presidentDto.getSurname());
            }
            if (Objects.nonNull(presidentDto.getTermFrom())) {
                president.setTermFrom(presidentDto.getTermFrom());
            }
            if (Objects.nonNull(presidentDto.getTermTo())) {
                president.setTermTo(presidentDto.getTermTo());
            }
            if (Objects.nonNull(presidentDto.getPoliticalParty())) {
                president.setPoliticalParty(presidentDto.getPoliticalParty());
            }
            return PresidentMapper.toDto(president);
        }).orElseThrow(() -> new EntityNotFoundException(PresidentsControllerExceptionMessages.ENTITY_FOR_PROVIDED_ID_NOT_EXISTS.getMessage()));

    }

    @Override
    public void deletePresidentById(Long id) {
        presidentsRepository.deleteById(id);
    }


}

package com.example.presidents.service.president;

import com.example.presidents.model.dto.PresidentDto;
import com.example.presidents.model.entity.President;
import com.example.presidents.model.mapper.PresidentMapper;
import com.example.presidents.repository.PresidentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

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
    public Set<PresidentDto> findPresidentByName(String name) {
        return presidentsRepository.findPresidentsByName(name).stream()
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
       return presidentsRepository.findById(presidentDto.getId()).map(president -> {
            president.setName(presidentDto.getName());
            president.setSurname(presidentDto.getSurname());
            president.setPoliticalParty(presidentDto.getPoliticalParty());
            president.setTermFrom(presidentDto.getTermFrom());
            president.setTermTo(presidentDto.getTermTo());
            return PresidentMapper.toDto(president);
        }).get();

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
        }).get();

    }

    @Override
    public void deletePresidentById(Long id) {
        presidentsRepository.deleteById(id);
    }


}

package com.example.presidents.service.president;

import com.example.presidents.model.entity.President;
import com.example.presidents.repository.PresidentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PresidentServiceImpl implements PresidentService{

    private final PresidentsRepository presidentsRepository;

    @Override
    public List<President> getAllPresidents() {
        return presidentsRepository.findAll();
    }

    @Override
    public President savePresident(President president) {
        return presidentsRepository.save(president);
    }
}

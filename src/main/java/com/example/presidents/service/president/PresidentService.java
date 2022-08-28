package com.example.presidents.service.president;

import com.example.presidents.model.entity.President;

import java.util.List;

public interface PresidentService {

    List<President> getAllPresidents() ;

    President savePresident(President president);
}

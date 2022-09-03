package com.example.presidents.exception.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PresidentsControllerExceptionMessages {

    ENTITY_FOR_PROVIDED_ID_NOT_EXISTS("Encja dla podanego ID nie istnieje"),

    ENTITY_FOR_PROVIDED_PARAMETER_NOT_EXISTS("Encja dla podanego parametru nie istnieje");



    private final String message;


}

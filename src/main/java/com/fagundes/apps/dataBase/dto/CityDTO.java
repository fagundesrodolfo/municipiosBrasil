package com.fagundes.apps.dataBase.dto;

public class CityDTO {

    public final Long id;
    public final String name;
    public final Long stateId;

    public CityDTO(Long id, String name, Long stateId) {
        this.id = id;
        this.name = name;
        this.stateId = stateId;
    }
}

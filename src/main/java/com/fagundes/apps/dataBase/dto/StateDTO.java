package com.fagundes.apps.dataBase.dto;

public class StateDTO {

    public long id;
    public String name;
    public String abbreviation;

    public StateDTO(long id, String name, String abbreviation) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
    }

}

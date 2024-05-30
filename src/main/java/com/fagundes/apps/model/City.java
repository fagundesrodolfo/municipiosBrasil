package com.fagundes.apps.model;

public class City {

    public final Long id;
    public final String name;
    public final Long stateId;
    public final String stateAbbreviation;
    public final String stateName;

    public City(Long id, String name, Long stateId, String stateAbbreviation, String stateName) {
        this.id = id;
        this.name = name;
        this.stateId = stateId;
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
    }

}

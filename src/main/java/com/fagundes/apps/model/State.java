package com.fagundes.apps.model;

import java.util.List;

public class State {

    public final Long id;
    public final String name;
    public final String abbreviation;
    public final List<City> listOfCity;

    public State(Long id, String name, String abbreviation, List<City> listOfCity) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.listOfCity = listOfCity;
    }
}

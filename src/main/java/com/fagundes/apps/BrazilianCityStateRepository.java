package com.fagundes.apps;

import com.fagundes.apps.model.City;
import com.fagundes.apps.model.State;
import com.fagundes.apps.dataBase.Cache;
import com.fagundes.apps.utils.NormalizeTextToSearch;
import java.util.*;

public class BrazilianCityStateRepository {

    private final Cache cache = new Cache();
    private final NormalizeTextToSearch normalizeText = new NormalizeTextToSearch();

    public BrazilianCityStateRepository() {

    }

    public List<City> findAllCities() {
        return cache.getListCity();
    }

    public List<City> findCitiesByStateId(long stateId) {
        Optional<State> optionalState = cache.getListState().stream()
                .filter(state -> state.id == stateId)
                .findFirst();

        if (optionalState.isPresent()) {
            return optionalState.get().listOfCity;
        } else {
            return new ArrayList<>();
        }
    }

    public List<City> findCitiesByStateAbbreviation(String stateAbbreviation) {
        if (!isValidTextToSearch(stateAbbreviation)) {
            return new ArrayList<>();
        }
        String abbreviationToSearchNormalized = normalizeText.normalize(stateAbbreviation);
        Optional<State> optionalState = cache.getListState().stream().filter(state -> {
            String abbreviationNormalized = normalizeText.normalize(state.abbreviation);
            return abbreviationNormalized.contains(abbreviationToSearchNormalized);
        }).findFirst();

        if (optionalState.isPresent()) {
            return optionalState.get().listOfCity;
        } else {
            return new ArrayList<>();
        }
    }

    public List<City> findCitiesByName(String name) {
        if (!isValidTextToSearch(name)) {
            return new ArrayList<>();
        }
        String nameToSearchNormalized = normalizeText.normalize(name);
        return cache.getListCity().stream().filter(city -> {
            String nameNormalized = normalizeText.normalize(city.name);
            return nameNormalized.contains(nameToSearchNormalized);
        }).toList();
    }

    public List<State> findAllStates() {
        return cache.getListState();
    }

    public List<State> findAllStatesByName(String stateName) {
        if (!isValidTextToSearch(stateName)) {
            return new ArrayList<>();
        }
        String stateToSearchNormalized = normalizeText.normalize(stateName);
        return cache.getListState().stream().filter(state -> {
            String nameNormalized = normalizeText.normalize(state.abbreviation);
            return nameNormalized.contains(stateToSearchNormalized);
        }).toList();
    }

    public List<State> findAllStatesByAbbreviation(String abbreviation) {
        if (!isValidTextToSearch(abbreviation)) {
            return new ArrayList<>();
        }
        String abbreviationToSearchNormalized = normalizeText.normalize(abbreviation);
        return cache.getListState().stream().filter(state -> {
            String abbreviationNormalized = normalizeText.normalize(state.abbreviation);
            return abbreviationNormalized.contains(abbreviationToSearchNormalized);
        }).toList();
    }

    private Boolean isValidTextToSearch(String text){
        return text != null && !text.isBlank();
    }
}

package com.fagundes.apps.dataBase;

import com.fagundes.apps.model.City;
import com.fagundes.apps.model.State;
import com.fagundes.apps.dataBase.dto.CityDTO;
import com.fagundes.apps.dataBase.dto.StateDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {

    private List<City> listCity = new ArrayList<City>();
    private List<State> listState = new ArrayList<State>();

    public Cache() {

    }

    public List<City> getListCity() {
        if (listCity.isEmpty()) {
            populateLists();
        }
        return listCity;
    }

    public List<State> getListState() {
        if (listState.isEmpty()) {
            populateLists();
        }
        return listState;
    }

    private void populateLists() {

        CityDataBase cityDataBase = new CityDataBase();
        StateDateBase stateDateBase = new StateDateBase();

        List<CityDTO> citiesDTOs = cityDataBase.findAll();
        Map<Long, StateDTO> stateDTOMap = new HashMap<Long, StateDTO>();
        stateDateBase.findAll().forEach(stateDTO -> stateDTOMap.put(stateDTO.id, stateDTO));

        citiesDTOs.forEach(cityDTO -> {
            StateDTO stateDTO = stateDTOMap.get(cityDTO.stateId);
            listCity.add(
                    new City(cityDTO.id, cityDTO.name, cityDTO.stateId, stateDTO.abbreviation, stateDTO.name)
            );
        });

        stateDTOMap.values().forEach(stateDTO -> listState.add(
                new State(
                        stateDTO.id,
                        stateDTO.name,
                        stateDTO.abbreviation,
                        listCity.stream().filter(city -> city.stateId == stateDTO.id).toList()))
        );
    }
}

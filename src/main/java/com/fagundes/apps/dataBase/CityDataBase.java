package com.fagundes.apps.dataBase;

import com.fagundes.apps.dataBase.dto.CityDTO;
import com.fagundes.apps.utils.FileReaderDelegate;

import java.util.ArrayList;
import java.util.List;

class CityDataBase {

    private final String CITY_FILE = "municipios.csv";
    private final int INDEX_ID = 0;
    private final int INDEX_NAME = 1;
    private final int INDEX_STATE_ID = 2;

    private final FileReaderDelegate fileReader = new FileReaderDelegate();

    CityDataBase() {

    }

    public List<CityDTO> findAll() {
        List<CityDTO> cities = new ArrayList<>();
        FileReaderDelegate.FileLineResult callBack = (CityDTOData) -> {
            if (CityDTOData.length == 3) {
                CityDTO CityDTO = mapCityDTODataToCityDTO(CityDTOData);
                if (CityDTO != null) {
                    cities.add(CityDTO);
                }
            }
        };
        fileReader.readFile(CITY_FILE, callBack);
        return cities;
    }

    private CityDTO mapCityDTODataToCityDTO(String[] CityDTOData) {
        if (CityDTOData.length == 3) {
            long id = Long.parseLong(CityDTOData[INDEX_ID]);
            String name = CityDTOData[INDEX_NAME];
            long stateId = Long.parseLong(CityDTOData[INDEX_STATE_ID]);
            return new CityDTO(id, name, stateId);
        } else {
            return null;
        }
    }
}

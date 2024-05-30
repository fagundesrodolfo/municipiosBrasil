package com.fagundes.apps.dataBase;

import com.fagundes.apps.dataBase.dto.StateDTO;
import com.fagundes.apps.utils.FileReaderDelegate;

import java.util.ArrayList;
import java.util.List;

class StateDateBase {

    private final String STATE_DATA_BASE = "unidade_federacao.csv";
    private final int INDEX_ID = 0;
    private final int INDEX_NAME = 1;
    private final int INDEX_ABBREVIATION = 2;

    private final FileReaderDelegate fileReader = new FileReaderDelegate();

    StateDateBase() {

    }

    public List<StateDTO> findAll() {
        List<StateDTO> stateDTOs = new ArrayList<>();
        FileReaderDelegate.FileLineResult callBack = (data) -> {
            StateDTO stateDTO = mapDataToStateDTO(data);
            if (stateDTO != null) {
                stateDTOs.add(stateDTO);
            }
        };
        fileReader.readFile(STATE_DATA_BASE, callBack);
        return stateDTOs;
    }

     private StateDTO mapDataToStateDTO(String[] data) {
        if (data.length == 3) {
            long id = Long.parseLong(data[INDEX_ID]);
            String name = data[INDEX_NAME];
            String abbreviation = data[INDEX_ABBREVIATION];
            return new StateDTO(id, name, abbreviation);
        } else {
            return null;
        }
    }
}

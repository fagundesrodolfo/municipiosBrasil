package com.fagundes.apps.utils;

import java.text.Normalizer;

public class NormalizeTextToSearch {
    public String normalize(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}

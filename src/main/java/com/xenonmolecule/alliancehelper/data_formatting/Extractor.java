package com.xenonmolecule.alliancehelper.data_formatting;

import com.google.gson.JsonObject;

public class Extractor {

    private String extractionFactor;

    public Extractor(String extractionFactor) {
        this.extractionFactor = extractionFactor;
    }

    public JsonObject extract(JsonObject parent) {
        String parentName = parent.getAsJsonPrimitive(extractionFactor).getAsString();
        parent.remove(extractionFactor);
        JsonObject output = new JsonObject();
        output.add(parentName,parent);
        return output;
    }

}
 
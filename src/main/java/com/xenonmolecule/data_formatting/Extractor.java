package com.xenonmolecule.data_formatting;

import com.google.gson.JsonObject;

/**
 * Created by MichaelRyan on 2/11/17.
 */
public class Extractor {

    String extractionFactor;

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
 
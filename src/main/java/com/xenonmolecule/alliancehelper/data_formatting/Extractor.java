package com.xenonmolecule.alliancehelper.data_formatting;

import com.google.gson.JsonObject;

public class Extractor {

    private String extractionFactor;
    private String parentName = "";
    private JsonObject parent = new JsonObject();

    public Extractor(String extractionFactor) {
        this.extractionFactor = extractionFactor;
    }

    public JsonObject extract(JsonObject parent) {
        String parentName = parent.getAsJsonPrimitive(extractionFactor).getAsString();
        parent.remove(extractionFactor);
        JsonObject output = new JsonObject();
        output.add(parentName,parent);

        this.parent = parent;
        this.parentName = parentName;

        return output;
    }

    public String getNameValue() {
        return parentName;
    }

    public JsonObject getInsideData() {
        return parent;
    }

}
 
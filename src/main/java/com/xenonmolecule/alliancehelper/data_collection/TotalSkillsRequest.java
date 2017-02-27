package com.xenonmolecule.alliancehelper.data_collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xenonmolecule.alliancehelper.data_formatting.SkillsFormatter;

public class TotalSkillsRequest extends Request {

    public TotalSkillsRequest(String sku) {
        super("https://api.vexdb.io/v1/get_skills?sku=" + sku);
    }

    public JsonObject getRawSkillsData() {
        return getFullResponseJSON();
    }

    public JsonArray getFormattedSkillsList() {
        return new SkillsFormatter(getResults(getFullResponse())).format().getAsJsonArray();
    }

}

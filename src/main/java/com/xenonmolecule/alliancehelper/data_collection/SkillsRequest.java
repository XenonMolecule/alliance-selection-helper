package com.xenonmolecule.alliancehelper.data_collection;

import com.google.gson.JsonObject;
import com.xenonmolecule.alliancehelper.data_formatting.SkillFormatter;

public class SkillsRequest extends Request {

    public SkillsRequest(String sku, String team) {
        super("https://api.vexdb.io/v1/get_skills?sku=" + sku + "&team=" + team);
    }

    public JsonObject getRawSkillsData() {
        return getFullResponseJSON();
    }

    public JsonObject getFormattedSkillsData() {
        SkillFormatter fmt = new SkillFormatter(getResults(getFullResponse()));
        return fmt.format().getAsJsonObject();
    }

}

package com.xenonmolecule.data_collection;

import com.google.gson.JsonObject;
import com.xenonmolecule.data_formatting.SkillFormatter;

/**
 * Created by MichaelRyan on 2/16/17.
 */
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

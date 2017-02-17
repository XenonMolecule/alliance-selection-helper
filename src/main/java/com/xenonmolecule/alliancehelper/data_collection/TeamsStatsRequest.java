package com.xenonmolecule.alliancehelper.data_collection;

import com.google.gson.JsonArray;
import com.xenonmolecule.alliancehelper.data_formatting.TeamsFormatter;

public class TeamsStatsRequest extends Request {

    public TeamsStatsRequest(String sku) {
        super("https://api.vexdb.io/v1/get_rankings?sku=" + sku);
    }

    public JsonArray getRawTeamsStats() {
        return getFullResponseJSON().getAsJsonArray("result");
    }

    public JsonArray getFormattedTeamsStats() {
        return new TeamsFormatter(getRawTeamsStats()).format().getAsJsonArray();
    }

}

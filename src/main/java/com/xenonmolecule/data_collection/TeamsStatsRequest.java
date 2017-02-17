package com.xenonmolecule.data_collection;

import com.google.gson.JsonArray;
import com.xenonmolecule.data_formatting.TeamsFormatter;

/**
 * Created by MichaelRyan on 2/11/17.
 */
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

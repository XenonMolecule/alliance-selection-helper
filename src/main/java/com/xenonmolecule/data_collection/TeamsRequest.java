package com.xenonmolecule.data_collection;

/**
 * Created by MichaelRyan on 1/31/17.
 */

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class TeamsRequest extends Request {

    public TeamsRequest(String sku) {
        super("https://api.vexdb.io/v1/get_teams?sku=" + sku);
    }

    public int getTeamCount() {
        return getResponseLength();
    }

    public JsonArray getRawTeamsList() {
        return getFullResponseJSON().getAsJsonArray("result");
    }

    public JsonArray getFormattedTeamsList() {
        JsonArray teamList = getRawTeamsList();
        int index = 0;
        TeamRequest dummyReq = new TeamRequest("2616E","RE-VRC-16-1346");
        for (JsonElement team : teamList) {
            teamList.set(index,dummyReq.formatTeamStats(team.getAsJsonObject()));
            index++;
        }
        return teamList;
    }

}

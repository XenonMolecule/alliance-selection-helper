package com.xenonmolecule.alliancehelper.data_collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xenonmolecule.alliancehelper.data_formatting.Extractor;

/**
 * Created by MichaelRyan on 2/18/17.
 */
public class Competition {

    private String sku;
    private JsonArray data = null;

    public Competition(String sku) {
        this.sku = sku;
    }

    /*public JsonArray getAllTeamData() {
        JsonArray game = new TeamsStatsRequest(sku).getFormattedTeamsStats();
        JsonArray skills = new TotalSkillsRequest(sku).getFormattedSkillsList();
        Extractor ext = new Extractor("team");
        JsonObject gameObj = new JsonObject();
        JsonObject skillsObj = new JsonObject();
        for(JsonElement team : game) {
            gameObj.add(ext.extract(team.getAsJsonObject()));
        }
        for(JsonElement team : skills) {
            skillsObj.add(ext.extract(team.getAsJsonObject()));
        }
        return gameObj;
    }*/

    /*public JsonArray getAllTeamData(boolean refresh) {
        if(!refresh && data!=null)
            return data;
        return getAllTeamData();
    }*/

    // Get a String array of all the teams in a competition
    public String[] getTeamsList() {
        JsonArray list = new TeamsRequest(sku).getRawTeamsList();
        String[] teams = new String[list.size()];
        int index = 0;
        for(JsonElement team : list) {
            teams[index] = team.getAsJsonObject().get("team").getAsString();
            index++;
        }
        return teams;
    }

}

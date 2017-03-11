package com.xenonmolecule.alliancehelper.data_collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xenonmolecule.alliancehelper.data_formatting.CompSimpleFormatter;
import com.xenonmolecule.alliancehelper.data_formatting.Extractor;
import java.util.ArrayList;

/**
 * Created by MichaelRyan on 2/18/17.
 */
public class Competition {

    private String sku;
    private JsonObject data = null;

    public Competition(String sku) {
        this.sku = sku;
    }

    public JsonObject getAllTeamData() {
        JsonArray game = new TeamsStatsRequest(sku).getFormattedTeamsStats();
        JsonArray skills = new TotalSkillsRequest(sku).getFormattedSkillsList();
        Extractor ext = new Extractor("team");
        JsonObject gameObj = new JsonObject();
        JsonObject skillsObj = new JsonObject();
        ArrayList<String> teamsList = new ArrayList<String>();
        for (JsonElement team : game) {
            ext.extract(team.getAsJsonObject());
            gameObj.add(ext.getNameValue(), ext.getInsideData());
            teamsList.add(ext.getNameValue());
        }
        for (JsonElement team : skills) {
            ext.extract(team.getAsJsonObject());
            skillsObj.add(ext.getNameValue(), ext.getInsideData());
        }
        for (String team : teamsList) {
            JsonObject teamSkills = new JsonObject();
            try {
                teamSkills = skillsObj.get(team).getAsJsonObject();
            } catch (NullPointerException e) {
                teamSkills.addProperty("totalSkills", 0.0);
                teamSkills.addProperty("driverSkills",0.0);
                teamSkills.addProperty("programmingSkills",0.0);
                teamSkills.addProperty("skillsRank",0.0);
            }
            JsonObject teamObj = gameObj.get(team).getAsJsonObject();
            teamObj.addProperty("totalSkills",teamSkills.getAsJsonPrimitive("totalSkills").getAsDouble());
            teamObj.addProperty("driverSkills",teamSkills.getAsJsonPrimitive("driverSkills").getAsDouble());
            teamObj.addProperty("programmingSkills",teamSkills.getAsJsonPrimitive("programmingSkills").getAsDouble());
            teamObj.addProperty("skillsRank",teamSkills.getAsJsonPrimitive("skillsRank").getAsDouble());
        }
        data = gameObj;
        return gameObj;
    }

    public JsonObject getAllTeamData(boolean refresh) {
        if (!refresh && data != null)
            return data;
        return getAllTeamData();
    }

    // Get a String array of all the teams in a competition
    public String[] getTeamsList() {
        JsonArray list = new TeamsRequest(sku).getRawTeamsList();
        String[] teams = new String[list.size()];
        int index = 0;
        for (JsonElement team : list) {
            teams[index] = team.getAsJsonObject().get("number").getAsString();
            index++;
        }
        return teams;
    }

    public JsonArray getMatchData() {
        return new MatchesRequest(sku).getAllMatches();
    }

    public JsonObject getSimpleTeamData(boolean refresh) {
        CompSimpleFormatter fmt = new CompSimpleFormatter(getAllTeamData(refresh),getTeamsList());
        return fmt.format().getAsJsonObject();
    }

    public String getSku() {
        return sku;
    }

}

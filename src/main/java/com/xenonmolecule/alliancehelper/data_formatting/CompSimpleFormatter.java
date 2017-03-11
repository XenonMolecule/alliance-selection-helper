package com.xenonmolecule.alliancehelper.data_formatting;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by MichaelRyan on 3/7/17.
 */
public class CompSimpleFormatter implements Formatter {

    JsonObject teams;
    String[] teamsList;

    public CompSimpleFormatter(JsonObject teamData, String[] teamsList) {
        this.teams = teamData;
        this.teamsList = teamsList;
    }

    @Override
    public JsonElement format() {
        JsonObject output = new JsonObject();
        for(String team : teamsList) {
            JsonArray data = new JsonArray();
            try {
                JsonObject teamObj = teams.get(team).getAsJsonObject();
                data.add(teamObj.get("rank").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("wins").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("losses").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("ties").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("ap").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("sp").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("trsp").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("maxScore").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("ccwm").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("opr").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("dpr").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("totalSkills").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("driverSkills").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("programmingSkills").getAsJsonPrimitive().getAsDouble());
                data.add(teamObj.get("skillsRank").getAsJsonPrimitive().getAsDouble());
                output.add(team, data);
            } catch (NullPointerException e) {};
        }
        return output;
    }

}

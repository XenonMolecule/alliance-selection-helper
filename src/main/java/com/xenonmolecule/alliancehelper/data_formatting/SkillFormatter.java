package com.xenonmolecule.alliancehelper.data_formatting;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SkillFormatter implements Formatter {

    JsonArray skills;

    public SkillFormatter(JsonArray skills){
        this.skills = skills;
    }

    public JsonElement format() {
        // Get Necessary Data
        int driver = 0, programming = 0, total = 0, rank = -5;
        String team = "";
        for(JsonElement challenge : skills) {
            JsonObject object = challenge.getAsJsonObject();
            switch (challenge.getAsJsonObject().getAsJsonPrimitive("type").getAsInt()){
                case 0:
                    driver = object.getAsJsonPrimitive("score").getAsInt();
                    break;
                case 1:
                    programming = object.getAsJsonPrimitive("score").getAsInt();
                    break;
                case 2:
                    total = object.getAsJsonPrimitive("score").getAsInt();
                    rank = object.getAsJsonPrimitive("rank").getAsInt();
                    team = object.getAsJsonPrimitive("team").getAsString();
                    break;
                default:
                    System.out.println("VEXDB Has added a new type of skills challenge, that is not yet handled...\n" +
                            "Please edit SkillsRequest.java to handle this new challenge.");
            }
        }

        // Build Output
        JsonObject res = new JsonObject();
        res.addProperty("team",team);
        res.addProperty("programmingSkills",programming);
        res.addProperty("driverSkills",driver);
        res.addProperty("totalSkills",total);
        res.addProperty("skillsRank",rank);

        return res;
    }

}

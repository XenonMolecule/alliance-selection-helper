package com.xenonmolecule.alliancehelper.data_formatting;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by MichaelRyan on 2/17/17.
 */
public class SkillsFormatter implements Formatter {

    JsonArray skills;

    public SkillsFormatter(JsonArray skills) {
        this.skills = skills;
    }

    public JsonElement format() {
        skills = divideByTeam(skills);
        int maxTotal = 1, maxDriver = 1, maxProgramming = 1, totalTeams = skills.size();
        int index = 0;
        // Format all teams and get max scores
        for(JsonElement team : skills) {
            SkillFormatter fmt = new SkillFormatter(team.getAsJsonArray());
            JsonObject skillsObj = fmt.format().getAsJsonObject();
            skills.set(index,skillsObj);
            if(skillsObj.get("totalSkills").getAsInt() > maxTotal)
                maxTotal = skillsObj.get("totalSkills").getAsInt();
            if(skillsObj.get("driverSkills").getAsInt() > maxDriver)
                maxDriver = skillsObj.get("driverSkills").getAsInt();
            if(skillsObj.get("programmingSkills").getAsInt() > maxProgramming)
                maxProgramming = skillsObj.get("programmingSkills").getAsInt();
            index++;
        }
        // Scale the data
        index = 0;
        for(JsonElement team : skills) {
            JsonObject teamObj = team.getAsJsonObject();
            // Get old data
            int total = teamObj.get("totalSkills").getAsInt(),
                    driver = teamObj.get("driverSkills").getAsInt(),
                    programming = teamObj.get("programmingSkills").getAsInt(),
                    rank = teamObj.get("skillsRank").getAsInt();

            // Remove old data
            teamObj.remove("totalSkills");
            teamObj.remove("driverSkills");
            teamObj.remove("programmingSkills");
            teamObj.remove("skillsRank");

            // Plug in new data
            teamObj.addProperty("totalSkills",(double)total/(double)maxTotal);
            teamObj.addProperty("driverSkills",(double)driver/(double)maxDriver);
            teamObj.addProperty("programmingSkills",(double)programming/(double)maxProgramming);
            teamObj.addProperty("skillsRank",Math.abs((double)(totalTeams-rank))/(double)totalTeams);

            skills.set(index,teamObj);
            index++;
        }
        return skills;
    }

    private JsonArray divideByTeam(JsonArray skillsArr) {
        JsonArray output = new JsonArray();
        // Skills array should always be divisible by three
        if(skillsArr.size() % 3 != 0) {
            System.out.println("[ERROR] Skills array from " +
                    skillsArr.get(0).getAsJsonObject().get("sku").getAsString() + " has a length of " +
                    skillsArr.size() + " which is not divisible by 3");
            return null;
        }
        for(int i = 0; i < skillsArr.size(); i += 3) {
            JsonArray team = new JsonArray();
            team.add(skillsArr.get(i));
            team.add(skillsArr.get(i+1));
            team.add(skillsArr.get(i+2));
            output.add(team);
        }
        return output;
    }

}

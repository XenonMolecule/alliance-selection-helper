package com.xenonmolecule.data_formatting;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;

/**
 * Created by MichaelRyan on 2/11/17.
 */
public class TeamsFormatter implements Formatter {

    JsonArray teams;

    public TeamsFormatter(JsonArray teams) {
        this.teams = teams;
    }

    public JsonElement format() {
        JsonArray formattedTeams = teams;
        int index = 0;
        int maxSP = 1, maxTRSP = 1, maxMaxScore = 1;
        double maxCCWM = 0.1, maxOPR = 0.1, maxDPR = 0.1;
        for(JsonElement team : formattedTeams) {
            JsonObject teamObj = team.getAsJsonObject();
            formattedTeams.set(index,new TeamFormatter(teamObj).format());
            index++;
            int sp = teamObj.getAsJsonPrimitive("sp").getAsInt();
            int trsp = teamObj.getAsJsonPrimitive("trsp").getAsInt();
            int maxScore = teamObj.getAsJsonPrimitive("max_score").getAsInt();
            double ccwm = teamObj.getAsJsonPrimitive("ccwm").getAsDouble();
            double opr = teamObj.getAsJsonPrimitive("opr").getAsDouble();
            double dpr = teamObj.getAsJsonPrimitive("dpr").getAsDouble();

            if(Math.abs(sp) > maxSP)
                maxSP = sp;
            if(Math.abs(trsp) > maxTRSP)
                maxTRSP = trsp;
            if(Math.abs(maxScore) > maxMaxScore)
                maxMaxScore = maxScore;
            if(Math.abs(ccwm) > maxCCWM)
                maxCCWM = ccwm;
            if(Math.abs(opr) > maxOPR)
                maxOPR = opr;
            if(Math.abs(dpr) > maxDPR)
                maxDPR = dpr;
        }

        for(JsonElement team : formattedTeams) {
            JsonObject teamObj = team.getAsJsonObject();
            double sp = (double) teamObj.getAsJsonPrimitive("sp").getAsInt() / maxSP;
            double trsp = (double) teamObj.getAsJsonPrimitive("trsp").getAsInt() / maxTRSP;
            double maxScore = (double) teamObj.getAsJsonPrimitive("maxScore").getAsInt() / maxMaxScore;
            double ccwm = teamObj.getAsJsonPrimitive("ccwm").getAsDouble() / maxCCWM;
            double opr =  teamObj.getAsJsonPrimitive("opr").getAsDouble() / maxOPR;
            double dpr = teamObj.getAsJsonPrimitive("dpr").getAsDouble() / maxDPR;

            teamObj.remove("sp");
            teamObj.remove("trsp");
            teamObj.remove("ccwm");
            teamObj.remove("opr");
            teamObj.remove("dpr");
            teamObj.remove("maxScore");

            teamObj.addProperty("sp",sp);
            teamObj.addProperty("trsp",trsp);
            teamObj.addProperty("maxScore",maxScore);
            teamObj.addProperty("ccwm",ccwm);
            teamObj.addProperty("opr",opr);
            teamObj.addProperty("dpr",dpr);
        }

        return formattedTeams;

    }

}

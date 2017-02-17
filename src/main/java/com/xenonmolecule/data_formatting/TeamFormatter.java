package com.xenonmolecule.data_formatting;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.xenonmolecule.data_collection.TeamsRequest;
import com.xenonmolecule.data_collection.TeamRequest;

/**
 * Created by MichaelRyan on 2/11/17.
 */
public class TeamFormatter implements Formatter {

    JsonObject team;

    public TeamFormatter(JsonObject team) {
        this.team = team;
    }

    public  JsonElement format() {
        // Get the relevant information
        String teamName, sku;
        int sp, maxScore, trsp, totalMatches, teamCount;
        double rank, wins, losses, ties, ap, opr, dpr, ccwm;

        teamName = team.getAsJsonPrimitive("team").getAsString();

        sp = team.getAsJsonPrimitive("sp").getAsInt();
        maxScore = team.getAsJsonPrimitive("max_score").getAsInt();
        trsp = team.getAsJsonPrimitive("trsp").getAsInt();

        opr = team.getAsJsonPrimitive("opr").getAsDouble();
        dpr = team.getAsJsonPrimitive("dpr").getAsDouble();
        ccwm = team.getAsJsonPrimitive("ccwm").getAsDouble();

        sku = team.getAsJsonPrimitive("sku").getAsString();

        totalMatches = new TeamRequest(teamName,sku).getTotalMatches();

        wins = (double) team.getAsJsonPrimitive("wins").getAsInt() / totalMatches;
        losses = (double) team.getAsJsonPrimitive("losses").getAsInt() / totalMatches;
        ties = (double) team.getAsJsonPrimitive("ties").getAsInt() / totalMatches;

        ap = (double) (team.getAsJsonPrimitive("ap").getAsInt()) / (totalMatches * 4.0);

        teamCount = new TeamsRequest(sku).getTeamCount();

        rank = Math.abs((teamCount - (double) team.getAsJsonPrimitive("rank").getAsInt()) / teamCount);

        // Build response
        JsonObject res = new JsonObject();
        res.addProperty("team",teamName);
        res.addProperty("rank",rank);
        res.addProperty("wins",wins);
        res.addProperty("losses",losses);
        res.addProperty("ties",ties);
        res.addProperty("ap",ap);
        res.addProperty("sp",sp);
        res.addProperty("opr",opr);
        res.addProperty("dpr",dpr);
        res.addProperty("ccwm",ccwm);
        res.addProperty("trsp",trsp);
        res.addProperty("maxScore",maxScore);

        return res;
    }

}

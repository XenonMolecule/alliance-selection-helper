package com.xenonmolecule.data_collection;

import com.google.gson.JsonObject;

/**
 * Created by MichaelRyan on 1/31/17.
 */
public class TeamRequest extends Request {

    String sku;

    public TeamRequest(String team, String sku) {
        super("https://api.vexdb.io/v1/get_rankings?sku=" + sku + "&team=" + team);
        this.sku = sku;
    }

    public int getTotalMatches() {
        JsonObject team = getFullResponseJSON().getAsJsonArray("result").get(0).getAsJsonObject();
        int wins, losses, ties;
        wins = team.getAsJsonPrimitive("wins").getAsInt();
        losses = team.getAsJsonPrimitive("losses").getAsInt();
        ties = team.getAsJsonPrimitive("ties").getAsInt();

        return wins + losses + ties;
    }

    // Desired output :
    // team, rank/total, wins/total, losses/total, ties/total, ap/total, sp, opr, dpr, ccwm, trsp, maxScore
    public JsonObject formatTeamStats(JsonObject teamReq) {
        // Get the relevant information
        String team;
        int sp, maxScore, trsp, totalMatches, teamCount;
        double rank, wins, losses, ties, ap, opr, dpr, ccwm;

        team = teamReq.getAsJsonPrimitive("team").getAsString();

        sp = teamReq.getAsJsonPrimitive("sp").getAsInt();
        maxScore = teamReq.getAsJsonPrimitive("max_score").getAsInt();
        trsp = teamReq.getAsJsonPrimitive("trsp").getAsInt();

        opr = teamReq.getAsJsonPrimitive("opr").getAsDouble();
        dpr = teamReq.getAsJsonPrimitive("dpr").getAsDouble();
        ccwm = teamReq.getAsJsonPrimitive("ccwm").getAsDouble();

        totalMatches = getTotalMatches();

        wins = (double) teamReq.getAsJsonPrimitive("wins").getAsInt() / totalMatches;
        losses = (double) teamReq.getAsJsonPrimitive("losses").getAsInt() / totalMatches;
        ties = (double) teamReq.getAsJsonPrimitive("ties").getAsInt() / totalMatches;

        ap = (double) (teamReq.getAsJsonPrimitive("ap").getAsInt()) / (totalMatches * 4.0);

        teamCount = new TeamsRequest(sku).getTeamCount();

        rank = Math.abs((teamCount - (double) teamReq.getAsJsonPrimitive("rank").getAsInt()) / teamCount);

        // Build response
        JsonObject res = new JsonObject();
        res.addProperty("team",team);
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

    public JsonObject getRawTeamData() {
        return getFullResponseJSON();
    }

    public JsonObject getFormattedTeamResponse() {
        return formatTeamStats(getRawTeamData().getAsJsonArray("result").get(0).getAsJsonObject());
    }


}

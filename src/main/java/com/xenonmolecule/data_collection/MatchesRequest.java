package com.xenonmolecule.data_collection;

import com.google.gson.JsonObject;

/**
 * Created by MichaelRyan on 1/28/17.
 */
public class MatchesRequest extends Request{

    public MatchesRequest(String sku) {
        super("https://api.vexdb.io/v1/get_matches?sku=" + sku);
    }

    // Desired output :
    // red1, red2, blue1, blue2, winner
    public JsonObject formatMatch(JsonObject match) {
        // Get the relevant information
        String red1, red2, red3, blue1, blue2, blue3, redSit, blueSit;
        int redScore, blueScore;
        red1 = match.getAsJsonPrimitive("red1").getAsString();
        red2 = match.getAsJsonPrimitive("red2").getAsString();
        red3 = match.getAsJsonPrimitive("red3").getAsString();

        blue1 = match.getAsJsonPrimitive("blue1").getAsString();
        blue2 = match.getAsJsonPrimitive("blue2").getAsString();
        blue3 = match.getAsJsonPrimitive("blue3").getAsString();

        redSit = match.getAsJsonPrimitive("redsit").getAsString();
        blueSit = match.getAsJsonPrimitive("bluesit").getAsString();

        redScore = match.getAsJsonPrimitive("redscore").getAsInt();
        blueScore = match.getAsJsonPrimitive("bluescore").getAsInt();
        // Process the data
        String rRed1, rRed2, rBlue1, rBlue2, winner;
            // Red (determine who is sitting)
        if(redSit.equals(red1)) {
            rRed1 =  red2;
            rRed2 = red3;
        } else if(redSit.equals(red2)) {
            rRed1 = red1;
            rRed2 = red3;
        } else {
            rRed1 = red1;
            rRed2 = red2;
        }

            // Blue (determine who is sitting)
        if(blueSit.equals(blue1)) {
            rBlue1 =  blue2;
            rBlue2 = blue1;
        } else if(blueSit.equals(blue2)) {
            rBlue1 = red1;
            rBlue2 = red3;
        } else {
            rBlue1 = red1;
            rBlue2 = red2;
        }

            // Determine winner
        if(redScore < blueScore) {
            winner = "blue";
        } else if(blueScore < redScore) {
            winner = "red";
        } else {
            winner = "tie";
        }

        // Build response
        JsonObject res = new JsonObject();
        res.addProperty("red1",rRed1);
        res.addProperty("red2",rRed2);
        res.addProperty("blue1",rBlue1);
        res.addProperty("blue2", rBlue2);
        res.addProperty("winner",winner);

        return res;
    }

}

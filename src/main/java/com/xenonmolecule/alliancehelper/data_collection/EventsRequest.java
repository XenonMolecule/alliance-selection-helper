package com.xenonmolecule.alliancehelper.data_collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * Created by MichaelRyan on 1/28/17.
 */
public class EventsRequest extends Request{

    public EventsRequest(String season, String program) {
        super("https://api.vexdb.io/v1/get_events?program=" + program.toLowerCase() + "&season=" + season.toLowerCase() + "&status=past");
    }

    // Defaults program to vrc
    public EventsRequest(String season) {
        super("https://api.vexdb.io/v1/get_events?program=vrc&season=" + season.toLowerCase() + "&status=past");
    }

    public String[] getEventSKUs() {
        JsonArray results = getFullResponseJSON().getAsJsonArray("result");
        String[] output = new String[getResponseLength()];
        int index = 0;
        for(JsonElement event : results) {
            output[index] = event.getAsJsonObject().getAsJsonPrimitive("sku").getAsString();
            index++;
        }
        return output;
    }

}

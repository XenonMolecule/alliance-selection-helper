package com.xenonmolecule.alliancehelper.data_collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MichaelRyan on 1/28/17.
 */
public class EventsRequest extends Request{

    Date startDate = null, endDate = null;

    public EventsRequest(String season, String program) {
        super("https://api.vexdb.io/v1/get_events?program=" + program.toLowerCase() + "&season=" + season.toLowerCase() + "&status=past");
    }

    public EventsRequest(String season, String program, Date startDate, Date endDate) {
        super("https://api.vexdb.io/v1/get_events?program=" + program.toLowerCase() + "&season=" + season.toLowerCase() + "&status=past");
        this.startDate = startDate;
        this.endDate = endDate;
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
            JsonObject eventObj = event.getAsJsonObject();
            if(startDate!=null && ((javax.xml.bind.DatatypeConverter.parseDateTime(eventObj.getAsJsonPrimitive("end").getAsString())).getTime().compareTo(startDate) == -1)){
                continue; // if event before start date, skip
            }
            if(endDate!=null && ((javax.xml.bind.DatatypeConverter.parseDateTime(eventObj.getAsJsonPrimitive("end").getAsString())).getTime().compareTo(endDate) == 1)) {
                continue; // if event after end date, skip
            }
            output[index] = eventObj.getAsJsonPrimitive("sku").getAsString();
            index++;
        }
        String[] output2 = new String[index];
        for(int i = 0; i < index; i ++) {
          output2[i] = output[i];
        }
        return output2;
    }

}

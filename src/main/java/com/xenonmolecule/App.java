package com.xenonmolecule;

import com.xenonmolecule.data_collection.EventsRequest;
import com.xenonmolecule.data_collection.Request;

public class App 
{
    public static void main( String[] args )
    {
        Request test = new Request("https://api.vexdb.io/v1/get_events");
        EventsRequest events = new EventsRequest("starstruck");
        String[] eventSKUs = events.getEventSKUs();
        for(int i = 0; i < eventSKUs.length; i ++) {
            System.out.println(eventSKUs[i]);
        }
    }
}

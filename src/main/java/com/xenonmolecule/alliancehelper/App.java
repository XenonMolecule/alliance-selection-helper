package com.xenonmolecule.alliancehelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xenonmolecule.alliancehelper.data_collection.Competition;
import com.xenonmolecule.alliancehelper.data_collection.EventsRequest;
import com.xenonmolecule.alliancehelper.data_collection.MatchesRequest;
import com.xenonmolecule.alliancehelper.gui.AppGui;

import java.util.Calendar;
import java.util.Date;

public class App 
{
    public static void main( String[] args )
    {
        new AppGui();
        /*Request test = new Request("https://api.vexdb.io/v1/get_events");
        EventsRequest events = new EventsRequest("starstruck");
        String[] eventSKUs = events.getEventSKUs();
        for(int i = 0; i < eventSKUs.length; i ++) {
            System.out.println(eventSKUs[i]);
        }
        */
        MatchesRequest matches = new MatchesRequest("RE-VRC-17-5208");
        System.out.println(matches.getAllMatches().toString());
        /*
        TeamRequest team = new TeamRequest("2616E","RE-VRC-17-5271");
        System.out.println(team.getFormattedTeamResponse().toString());*/
        /*TeamsStatsRequest stats = new TeamsStatsRequest("RE-VRC-16-1346");
        System.out.println(stats.getFormattedTeamsStats().toString());
        Extractor ext = new Extractor("team");
        System.out.println(ext.extract(stats.getFormattedTeamsStats().getAsJsonArray().get(0).getAsJsonObject()));
        TotalSkillsRequest skills = new TotalSkillsRequest("RE-VRC-16-1346");
        System.out.println(skills.getFormattedSkillsList().toString());*/
        Competition sparta = new Competition("RE-VRC-16-1346");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //System.out.println(gson.toJson(sparta.getAllTeamData(true)));
        System.out.println(gson.toJson(sparta.getSimpleTeamData(true)));

        // Test the date version of get events
        Calendar cal = Calendar.getInstance();
        cal.set(2015,1,1);
        EventsRequest eReq = new EventsRequest("starstruck","vrc",cal.getTime(),new Date());
        String[] eventSKUs = eReq.getEventSKUs();
        // for(int i = 0; i < eventSKUs.length; i ++) {
        //     System.out.println(i + " - " + eventSKUs[i]);
        // }
    }
}

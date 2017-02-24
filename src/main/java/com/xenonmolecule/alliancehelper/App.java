package com.xenonmolecule.alliancehelper;

import com.xenonmolecule.alliancehelper.data_collection.Competition;
import com.xenonmolecule.alliancehelper.data_collection.TeamsStatsRequest;
import com.xenonmolecule.alliancehelper.data_collection.TotalSkillsRequest;
import com.xenonmolecule.alliancehelper.data_formatting.Extractor;

public class App 
{
    public static void main( String[] args )
    {
        /*Request test = new Request("https://api.vexdb.io/v1/get_events");
        EventsRequest events = new EventsRequest("starstruck");
        String[] eventSKUs = events.getEventSKUs();
        for(int i = 0; i < eventSKUs.length; i ++) {
            System.out.println(eventSKUs[i]);
        }
        MatchesRequest matches = new MatchesRequest("RE-VRC-17-5208");
        System.out.println(matches.getAllMatches().toString());
        TeamRequest team = new TeamRequest("2616E","RE-VRC-17-5271");
        System.out.println(team.getFormattedTeamResponse().toString());*/
        /*TeamsStatsRequest stats = new TeamsStatsRequest("RE-VRC-16-1346");
        System.out.println(stats.getFormattedTeamsStats().toString());
        Extractor ext = new Extractor("team");
        System.out.println(ext.extract(stats.getFormattedTeamsStats().getAsJsonArray().get(0).getAsJsonObject()));
        TotalSkillsRequest skills = new TotalSkillsRequest("RE-VRC-16-1346");
        System.out.println(skills.getFormattedSkillsList().toString());*/
        Competition sparta = new Competition("RE-VRC-16-1346");
        System.out.println(sparta.getAllTeamData(true));
    }
}

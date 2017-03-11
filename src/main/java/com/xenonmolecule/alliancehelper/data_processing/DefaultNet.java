package com.xenonmolecule.alliancehelper.data_processing;

import com.google.gson.*;
import com.xenonmolecule.alliancehelper.data_collection.Competition;
import com.xenonmolecule.alliancehelper.data_collection.EventsRequest;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by MichaelRyan on 3/3/17.
 */
public class DefaultNet extends VexNet {

    boolean debug = false;

    public DefaultNet(String location) {
        super(location);
    }

    public DefaultNet(NeuralNetwork network) {
        super(network);
    }

    @Override
    public double[] process(String sku, String red1, String red2, String blue1, String blue2) {
        return process(new Competition(sku),red1,red2,blue1,blue2);
    }

    // Process the given data and return output in the following form
    // double array of length 2
    //   [confidence red will win, confidence blue will win]
    @Override
    public double[] process(Competition comp, String red1, String red2, String blue1, String blue2) {
        // Get input data
        double[] inputArr = getTeamsData(comp, red1, red2, blue1, blue2);

        // Get or construct network
        NeuralNetwork network = getNetwork();
        if(network == null) {
            network = new MultiLayerPerceptron(TransferFunctionType.SIGMOID,60,100,50,2);
        }

        // Feed Forward through the Network
        network.setInput(inputArr);
        network.calculate();

        // Return the output
        return network.getOutput();
    }

    @Override
    public void train(Date start, Date end) {
        printDebug("Training");

        String[] events = new EventsRequest("starstruck","vrc",start,end).getEventSKUs();

        // Get or construct network
        NeuralNetwork network = getNetwork();
        if(network == null) {
            network = new MultiLayerPerceptron(TransferFunctionType.SIGMOID,60,100,50,2);
        }

        // Collect the data
        DataSet data = collectData(events);

        //Actually run the training code
        network.learn(data);
        setNetwork(network);
    }

    @Override
    public void train() {
        Calendar cal = Calendar.getInstance();
        cal.set(2015,1,1);
        train(cal.getTime(), new Date());
    }

    @Override
    public double test(Date start, Date end) {
        printDebug("Testing");

        String[] events = new EventsRequest("starstruck","sku",start,end).getEventSKUs();

        // Get or construct network
        NeuralNetwork network = getNetwork();
        if(network == null) {
            network = new MultiLayerPerceptron(TransferFunctionType.SIGMOID,60,100,50,2);
        }

        //Collect the data
        DataSet data = collectData(events);

        int timesCorrect = 0;
        int total = 0;

        // Actually test the network
        for(DataSetRow dataRow : data.getRows()) {
            network.setInput(dataRow.getInput());
            network.calculate();
            if(getWinner(network.getOutput()) == getWinner(dataRow.getDesiredOutput()))
                timesCorrect++;
            total++;
        }

        // Return % (in decimal form) of correct answers
        return (double) timesCorrect / (double) total;
    }

    // Returns true if red won, false if blue
    // Input is double array output from network
    // Example input : [1.0,0.0] <- red wins (true)
    private boolean getWinner(double[] chances) {
        if(chances.length == 2) {
            return (chances[0] >= chances[1]);
        }
        System.out.println("The given input was to getWinner in the neural network was not of the proper length");
        return true;
    }

    @Override
    public double test() {
        Calendar cal = Calendar.getInstance();
        cal.set(2015,1,1);
        return test(cal.getTime(), new Date());
    }

    private double[] getTeamsData(JsonObject teamData, String red1, String red2, String blue1, String blue2) {
        JsonArray red1Arr = teamData.get(red1).getAsJsonArray();
        JsonArray red2Arr = teamData.get(red2).getAsJsonArray();
        JsonArray blue1Arr = teamData.get(blue1).getAsJsonArray();
        JsonArray blue2Arr = teamData.get(blue2).getAsJsonArray();
        double[] output = new double[red1Arr.size()*4];
        ArrayList<JsonArray> teams = new ArrayList<>();
        teams.add(red1Arr);
        teams.add(red2Arr);
        teams.add(blue1Arr);
        teams.add(blue2Arr);
        for(int x = 0; x < teams.size(); x ++){
            for(int i = 0; i < teams.get(x).size(); i ++) {
                output[i + (x*teams.get(x).size())] = teams.get(x).get(i).getAsJsonPrimitive().getAsDouble();
            }
        }
        return output;
    }

    private double[] getTeamsData(Competition comp, String red1, String red2, String blue1, String blue2) {
        return getTeamsData(comp.getSimpleTeamData(false),red1,red2,blue1,blue2);
    }

    public double[] getWinnerArr(JsonObject match) {
        if(match.get("winner").getAsString().equals("red"))
            return new double[] {1.0, 0.0};
        else if(match.get("winner").getAsString().equals("blue")) {
            return new double[] {0.0,1.0};
        } else {
            return new double[] {0.0,0.0};
        }
    }

    private DataSet collectData(String[] events) {
        printDebug("Collecting Data");
        DataSet data = new DataSet(60,2);
        for(int index = 0; index < events.length; index++) {
            Date startTime = new Date();
            Competition comp = new Competition(events[index]);
            JsonObject teams = comp.getSimpleTeamData(true);
            JsonArray matches = comp.getMatchData();
            for(JsonElement match : matches) {
                JsonObject matchObj = match.getAsJsonObject();
                try {
                    data.addRow(new DataSetRow(getTeamsData(teams, matchObj.get("red1").getAsString(), matchObj.get("red2").getAsString(),
                            matchObj.get("blue1").getAsString(), matchObj.get("blue2").getAsString()), getWinnerArr(matchObj)));
                } catch (NullPointerException e) {
                    // Somebody forgot to put in team data for one of these teams... >:(
                    // Now this is a wasted match, so this catch does nothing
                }
            }
            printDebug("Gathering Data (" + index + "/" + events.length + ") [" + getDateDiff(startTime, new Date(), TimeUnit.SECONDS) + " seconds]");
        }
        return data;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void printDebug(String output) {
        if(debug)
            System.out.println("[DefaultNetwork] " + output);
    }

    private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}

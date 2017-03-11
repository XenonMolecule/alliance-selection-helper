package com.xenonmolecule.alliancehelper.data_processing;

import com.xenonmolecule.alliancehelper.data_collection.Competition;
import org.neuroph.core.NeuralNetwork;
import java.util.Date;

/**
 * Created by MichaelRyan on 3/1/17.
 */
public abstract class VexNet {

    String location;
    NeuralNetwork network;

    public VexNet(String location) {
        this.location = location;
        if(location!=null)
            this.network = NeuralNetwork.createFromFile(location);
        else
            this.network = null;
    }

    public VexNet(NeuralNetwork network) {
        this.location = null;
        this.network = network;
    }

    public void saveNetwork(String location) {
        this.location = location;
        if(location!=null)
            network.save(location);
        else
            System.out.println("Tried to save network, but no path provided");
    }

    public void saveNetwork() {
        if(location!=null)
            network.save(location);
        else
            System.out.println("Tried to save network, but no path provided");
    }

    public void loadNetwork(String location) {
        this.location = location;
        if(this.location!=null)
            this.network = NeuralNetwork.createFromFile(location);
        else
            System.out.println("Tried to load network, but no path provided");
    }

    public void loadNetwork() {
        if(location!=null)
            this.network = NeuralNetwork.createFromFile(location);
        else
            System.out.println("Tried to load network, but no path provided");
    }

    public void setLocation(String path) {
        this.location = path;
    }

    String getLocation() {
        return location;
    }

    NeuralNetwork getNetwork() {
        return network;
    }

    void setNetwork(NeuralNetwork network) {
        this.network = network;
    }

    public abstract double[] process(String sku, String red1, String red2, String blue1, String blue2);

    public abstract double[] process(Competition comp, String red1, String red2, String blue1, String blue2);

    public abstract void train(Date start, Date end);

    public abstract void train();

    public abstract double test(Date start, Date end);

    public abstract double test();

}

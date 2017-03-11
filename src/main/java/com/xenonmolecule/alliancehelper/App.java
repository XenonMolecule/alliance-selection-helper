package com.xenonmolecule.alliancehelper;

import com.xenonmolecule.alliancehelper.data_processing.DefaultNet;
import com.xenonmolecule.alliancehelper.gui.AppGui;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;
import java.text.DecimalFormat;

public class App 
{
    public static void main( String[] args )
    {
        new AppGui();
        DecimalFormat fmt = new DecimalFormat("#0.0##%");
        DefaultNet network = new DefaultNet(new MultiLayerPerceptron(TransferFunctionType.SIGMOID,60,100,50,2));
        network.setDebug(true);
        System.out.println("Training network...");
        network.train();
        System.out.println("\nTesting network...");
        System.out.println(fmt.format(network.test()) + " Accuracy");
    }
}

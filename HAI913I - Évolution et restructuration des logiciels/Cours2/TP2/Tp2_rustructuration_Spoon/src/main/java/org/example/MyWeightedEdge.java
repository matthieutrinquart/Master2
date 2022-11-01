package org.example;

import org.jgrapht.graph.DefaultWeightedEdge;

import java.text.NumberFormat;

public class MyWeightedEdge extends DefaultWeightedEdge {
    @Override
    public String toString() {
        double roundOff = Math.round(getWeight() * 1000.0) / 1000.0;
        return Double.toString(roundOff);
    }
}

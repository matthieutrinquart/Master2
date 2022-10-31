package org.example;

public class Edge {

    private Cluster c1;
    private Cluster c2;

    private float couplage;


    public Edge(Cluster c1, Cluster c2, float couplage) {
        this.c1 = c1;
        this.c2 = c2;
        this.couplage = couplage;
    }

    public Cluster getC1() {
        return c1;
    }

    public Cluster getC2() {
        return c2;
    }

    public float getCouplage() {
        return couplage;
    }
}

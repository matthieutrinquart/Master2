package org.example;

import java.util.ArrayList;

public class Cluster {

    private String nom;
    private ArrayList<Edge> noeud;

    public Cluster(String nom) {
        this.nom = nom;
        this.noeud = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Edge> getCouplage() {
        return noeud;
    }
    public void addNoeud(Edge e){
        noeud.add(e);
    }
}

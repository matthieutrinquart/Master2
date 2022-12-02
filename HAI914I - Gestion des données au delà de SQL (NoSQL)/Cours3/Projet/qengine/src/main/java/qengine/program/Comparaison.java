package qengine.program;

import qengine.program.QueryEngine.Jena;
import qengine.program.QueryEngine.QEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Comparaison {




    public static void verificationJena(List<String> JenaRequest, List<String> QEngineRequest) {
        ArrayList<String> difference = new ArrayList<>();

        for (String j : QEngineRequest) {
            if (! JenaRequest.contains(j)) {
                difference.add(j);
            }
        }

        for (String d : difference) {
            System.out.println(d);
        }
        if (! difference.isEmpty()) { System.out.println("\n\n\n\n"); }

/*
        boolean result = true;
        for (String j : JenaRequest) {
            if (!QEngineRequest.contains(j)) {
                result = false;
            }
        }

        for (String j : QEngineRequest) {
            if (!JenaRequest.contains(j)) {
                result = false;
            }
        }
        System.out.println(result);
        if(!result){

        }*/
    }
}

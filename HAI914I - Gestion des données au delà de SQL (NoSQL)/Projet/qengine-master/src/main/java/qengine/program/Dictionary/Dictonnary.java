package qengine.program.Dictionary;


import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.AbstractRDFHandler;
import qengine.program.MainRDFHandler;

import java.io.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Dictonnary {

    HashMap<Integer, String> map1;

    HashMap<String, Integer> map2;
    int index;

    public Dictonnary() {
        this.map1 = new HashMap<>();
        this.map2 = new HashMap<>();
        index = 0;
    }

    /*
    TODO:                     k, v .slpit(",")

     */

    public void saveDictionnary(File f) {

    }
    public static Dictionary loadDictionary(File f) {
        return null ;
    }


    public void add(String attribut) {
        if (!map1.containsValue(attribut)) {
            map1.put(index, attribut);
            map2.put(attribut,index);
            ++index;
        }
    }

    public int encode(String attribut){
        return map2.get(attribut);
    }

    public String encode(int val){
        return map1.get(val);
    }

    public void printDictionaty(){
        for (Map.Entry m : map1.entrySet()) {
            System.out.println("ID: "+m.getKey()+", Nom: "+m.getValue());
        }

    }
}

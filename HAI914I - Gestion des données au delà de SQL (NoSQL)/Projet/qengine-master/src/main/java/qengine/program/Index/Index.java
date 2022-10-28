package qengine.program.Index;

import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.util.Statements;
import org.eclipse.rdf4j.rio.helpers.AbstractRDFHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Index {

    /** ------------------------------------------------------------------- */
    //          S             P                     O
    HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> spo ;

    //          S              O                    P
    HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> sop ;

    /** ------------------------------------------------------------------- */
    //          P             S                     O
    HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> pso ;

    //          P              O                    S
    HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> pos ;

    /** ------------------------------------------------------------------- */
    //          O             S                     P
    HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> osp ;

    //          O              P                    S
    HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> ops ;

    /** ------------------------------------------------------------------- */


    private static Index instance = null;
    public static Index getInstance() {
        if (instance == null) { instance = new Index() ; }
        return instance;
    }

    public Index() {
        spo = new HashMap<>() ; sop = new HashMap<>() ;
        pso = new HashMap<>() ; pos = new HashMap<>() ;
        osp = new HashMap<>() ; ops = new HashMap<>() ;
    }

    public void indexStatement(Statement s) {
        s.getSubject() s.getObject() s.getPredicate()
        spo.putIfAbsent(Dicot.get(s.getSubject()), ) ;






        /*
        int s = s.getSubject(
        int p = s.getPredicate(
        int o = s.object

        (spo) ->
        si s exist:
            si p exist:
                si o exist:
                    hm<s>.get(s).get(p).add(o) ;
                else:
                    create ar<o>
                    hm<p>.put(ar<O>)
            else:
                create hm<p>
                create ar<o>
                hm<p>.put(ar<O>)
        else:
            create hm<s>
            create hm<p>
            create ar<o>
            hm<p>.put(ar<O>)
            hm<S>.put( hm<p>)




         */

    }
}
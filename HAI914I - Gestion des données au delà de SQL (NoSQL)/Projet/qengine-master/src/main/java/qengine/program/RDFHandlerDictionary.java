package qengine.program;

import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.helpers.AbstractRDFHandler;
import qengine.program.Dictionary.Dictonnary;

public class RDFHandlerDictionary  extends AbstractRDFHandler {


    private Dictonnary dictonnary;

    public RDFHandlerDictionary() {
        this.dictonnary = new Dictonnary();
    }

    public RDFHandlerDictionary(Dictonnary dictonnary) {
        this.dictonnary = dictonnary;
    }

    @Override
    public void handleStatement(Statement st) {
        System.out.println("coucou");
        dictonnary.add(st.getSubject().toString());
        dictonnary.add(st.getPredicate().toString());
        dictonnary.add(st.getObject().toString());
    };


    public Dictonnary getDictonnary(){
        return dictonnary;
    }
}

package org.example;


import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;
import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.filter.TypeFilter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class Parser {
    private String path;

    public static final String jrePath = "/usr/lib/jvm/jrt-fs.jar";
    private DirectedWeightedPseudograph<String, MyWeightedEdge> graph;

    private DirectedWeightedPseudograph<String, MyWeightedEdge> graphcluster;

    private SimpleDirectedGraph<String, MyWeightedEdge> dendogramme;
    private Launcher parser;
    public Parser(String path,int etape) throws IOException {
        this.path = path;
        ArrayList<String> f = Scan(path);
        this.graph = Parse(f);
        DirectedWeightedPseudograph<String, MyWeightedEdge> g1 = newgraph(this.graph);
        this.graphcluster = HierarchieCluster(g1,etape);
        DirectedWeightedPseudograph<String, MyWeightedEdge> g2 = newgraph(this.graph);
        this.dendogramme = DrawDendrogramme(g2);
    }
    public CtModel AST(String Path) throws IOException {
        parser = new Launcher(); // create launcher
        parser.addInputResource(Path); // set project source path
        return parser.buildModel();
    }

    public DirectedWeightedPseudograph Parse(ArrayList<String> f) throws IOException {
        DirectedWeightedPseudograph g =new DirectedWeightedPseudograph<>(MyWeightedEdge.class);

        for(String path : f){
            CtModel model =  AST(path);
            for (CtType type : model.getAllTypes()){
                HashMap<String,Integer> map = new HashMap<>();
                AtomicInteger nbappel = new AtomicInteger();
                //parcourt les methodes de cette classe
                System.out.println(type.getSimpleName());
                for(Object method : type.getMethods()){
                    if (!g.containsVertex(type.getSimpleName())) {
                        //On enregistre la méthode dans le graph
                        g.addVertex(type.getSimpleName());

                    }
                    CtMethod p = (CtMethod) method;
                    System.out.println("    "+p.getSimpleName());
                    p.filterChildren(new TypeFilter(CtInvocation.class)).forEach(inv->{
                        nbappel.incrementAndGet();
                        CtInvocation<?> invocation = (CtInvocation<?>) inv;
                        //System.out.println("        " +inv);
                        if (!g.containsVertex(invocation.getExecutable().getDeclaringType().getSimpleName())) {
                            //si la méthode n'a pas deja été enregistrer on le met dans le graph
                            g.addVertex(invocation.getExecutable().getDeclaringType().getSimpleName());
                        }
                        if (!map.containsKey(type.getSimpleName()+":"+ invocation.getExecutable().getDeclaringType().getSimpleName())) {
                            map.put(type.getSimpleName()+":"+ invocation.getExecutable().getDeclaringType().getSimpleName(),1);
                            //g.setEdgeWeight(e, 1);
                        } else {
                            map.put(type.getSimpleName()+":"+invocation.getExecutable().getDeclaringType().getSimpleName(), map.get(type.getSimpleName()+":"+ invocation.getExecutable().getDeclaringType().getSimpleName())+1);
                            //g.setEdgeWeight(e, ((float)g.getEdgeWeight(e) + 1));
                        }
                        System.out.println("        " +invocation.getExecutable().getDeclaringType().getSimpleName());

                    });

                }
                for (Map.Entry m : map.entrySet()) {
                    int i = (Integer) m.getValue();
                    String arret =(String) m.getKey();
                    String[] arretes = arret.split(":");
                    DefaultWeightedEdge e = (DefaultWeightedEdge)g.addEdge(arretes[0],arretes[1]);
                    g.setEdgeWeight(e, (float)i/ nbappel.get());

                }
            }


        }


        return g;

    }

    public String[] ClusterProche(DirectedWeightedPseudograph graph){

        String[] ret = new String[2];
        float valmax = 0;
        for (Object m : graph.vertexSet()) {
            Set<MyWeightedEdge> edges = graph.outgoingEdgesOf(m.toString());
            for(MyWeightedEdge e : edges){
                if(Float.valueOf(e.toString()) > valmax){
                    ret[0] = m.toString();
                    ret[1] = graph.getEdgeTarget(e).toString();
                    valmax = Float.valueOf(e.toString());
                }
              //  System.out.println(e +  " " + m.toString() + " " + graph.getEdgeTarget(e));
            }
        }

        return ret;
    }

    public DirectedWeightedPseudograph CreateCluster(DirectedWeightedPseudograph graph, String[] ex){

        graph.addVertex(ex[0]+":"+ex[1]);

        Set<MyWeightedEdge> edgesoutput1 = graph.outgoingEdgesOf(ex[0]);
        for(MyWeightedEdge e : edgesoutput1){
            if(!graph.containsEdge(graph.addEdge(ex[0]+":"+ex[1],graph.getEdgeTarget(e)))){
                DefaultWeightedEdge tampon = (DefaultWeightedEdge)graph.addEdge(ex[0]+":"+ex[1],graph.getEdgeTarget(e));
                graph.setEdgeWeight(tampon, Float.valueOf(e.toString()));
                graph.removeEdge(e);
            }else{
                DefaultWeightedEdge arret = (DefaultWeightedEdge)graph.getEdge(ex[0]+":"+ex[1],graph.getEdgeTarget(e));
                graph.setEdgeWeight(arret, (Float.valueOf(arret.toString()) +Float.valueOf(e.toString()))/2 );
            }
            //  System.out.println(e +  " " + m.toString() + " " + graph.getEdgeTarget(e));
        }

        Set<MyWeightedEdge> edgesoutput2 = graph.outgoingEdgesOf(ex[1]);
        for(MyWeightedEdge e : edgesoutput2){
            if(!graph.containsEdge(graph.addEdge(ex[0]+":"+ex[1],graph.getEdgeTarget(e)))){
                DefaultWeightedEdge tampon = (DefaultWeightedEdge)graph.addEdge(ex[0]+":"+ex[1],graph.getEdgeTarget(e));
                graph.setEdgeWeight(tampon, Float.valueOf(e.toString()));
                graph.removeEdge(e);
            }else{
                DefaultWeightedEdge arret = (DefaultWeightedEdge)graph.getEdge(ex[0]+":"+ex[1],graph.getEdgeTarget(e));
                graph.setEdgeWeight(arret, (Float.valueOf(arret.toString()) +Float.valueOf(e.toString()))/2 );
            }
            //  System.out.println(e +  " " + m.toString() + " " + graph.getEdgeTarget(e));
        }

        Set<MyWeightedEdge> edgesinput1 = graph.incomingEdgesOf(ex[0]);
        for(MyWeightedEdge e : edgesinput1){
            if(!graph.containsEdge(graph.addEdge(graph.getEdgeTarget(e),ex[0]+":"+ex[1]))){
                DefaultWeightedEdge tampon = (DefaultWeightedEdge)graph.addEdge(graph.getEdgeTarget(e),ex[0]+":"+ex[1]);
                graph.setEdgeWeight(tampon, Float.valueOf(e.toString()));
                graph.removeEdge(e);
            }else{
                DefaultWeightedEdge arret = (DefaultWeightedEdge)graph.getEdge(graph.getEdgeTarget(e),ex[0]+":"+ex[1]);
                graph.setEdgeWeight(arret, (Float.valueOf(arret.toString()) +Float.valueOf(e.toString()))/2 );
            }
            //  System.out.println(e +  " " + m.toString() + " " + graph.getEdgeTarget(e));
        }

        Set<MyWeightedEdge> edgesinput2 = graph.incomingEdgesOf(ex[1]);
        for(MyWeightedEdge e : edgesinput2) {
            if(!graph.containsEdge(graph.addEdge(graph.getEdgeTarget(e),ex[0]+":"+ex[1]))){
                DefaultWeightedEdge tampon = (DefaultWeightedEdge)graph.addEdge(graph.getEdgeTarget(e),ex[0]+":"+ex[1]);
                graph.setEdgeWeight(tampon, Float.valueOf(e.toString()));
                graph.removeEdge(e);
            }else{
                DefaultWeightedEdge arret = (DefaultWeightedEdge)graph.getEdge(graph.getEdgeTarget(e),ex[0]+":"+ex[1]);
                graph.setEdgeWeight(arret, (Float.valueOf(arret.toString()) +Float.valueOf(e.toString()))/2 );
            }
        }
        graph.removeVertex(ex[0]);
        graph.removeVertex(ex[1]);

        return graph;
    }

    public DirectedWeightedPseudograph HierarchieCluster(DirectedWeightedPseudograph graph, int i){

        //graph.getAllEdges();
        int index = 0;
        String[] ex ={"",""};
        graph.vertexSet().size();
        while(graph.vertexSet().size() > 1&& i != index){
            System.out.println(index);
            for (Object m : graph.vertexSet()) {
                System.out.println(m.toString());
                Set<MyWeightedEdge> edges = graph.outgoingEdgesOf(m.toString());
                for(MyWeightedEdge e : edges){
                    System.out.println("    " + e.toString() + " " + graph.getEdgeTarget(e));
                }
                System.out.println(graph.vertexSet().size());
            }
            ex = ClusterProche(graph);
            if(ex[0] == null){
                break;
            }
            System.out.println(ex[0] + "    " + ex[1]);
            graph = CreateCluster(graph, ex);
            ++index;

        }

        return graph;

    }
    public DirectedWeightedPseudograph newgraph(DirectedWeightedPseudograph<String, MyWeightedEdge> graph){
        DirectedWeightedPseudograph g =new DirectedWeightedPseudograph<>(MyWeightedEdge.class);
        for (Object m : graph.vertexSet()) {

            g.addVertex(m.toString());
        }
        for (Object m : graph.vertexSet()) {
            Set<MyWeightedEdge> edges = graph.outgoingEdgesOf(m.toString());
            for(MyWeightedEdge e : edges){
                DefaultWeightedEdge val = (DefaultWeightedEdge)g.addEdge(m.toString(),graph.getEdgeTarget(e).toString() );
                g.setEdgeWeight(val, Float.valueOf(e.toString()));
            }


        }
        return g;

    }

    //permet de lire un fichier et retourne son contenu en String
    public String readFileToString(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        char[] buf = new char[10];
        int numRead = 0;
        //récupère chaque ligne du fichier dans un buffer
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }

        reader.close();

        return  fileData.toString();
    }

    public void exportGraph(SimpleDirectedGraph graph , String filename) throws IOException, ExecutionException, InterruptedException {

        JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter =
                new JGraphXAdapter<String, DefaultWeightedEdge>(graph);
        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());
        BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 2, new Color(0f,0f,0f,.5f), true, null);
        File imgFile = new File(filename+".png");
        ImageIO.write(image, "PNG", imgFile);
    }
    public void exportGraph(DirectedWeightedPseudograph graph , String filename) throws IOException, ExecutionException, InterruptedException {
        JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter =
                new JGraphXAdapter<String, DefaultWeightedEdge>(graph);
        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());
        BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 2, new Color(0f,0f,0f,.5f), true, null);
        File imgFile = new File(filename + ".png");
        ImageIO.write(image, "PNG", imgFile);
    }


    //parcrourt récursivement le projet
    public ArrayList<String> Scan(String Path){

        ArrayList<String> files = new ArrayList<String>();
        File folder = new File(Path);
        for(File f : folder.listFiles())
        {
            //si l'element est un dossier parcourir ce dossier.
            if(f.isDirectory())
            {
                ArrayList<String> tampon = Scan(f.getPath());
                for (String tp:tampon) {
                    files.add(tp);

                }
            } else
            {   //si le nom du fichier ce termine par ".java" alors enregistrer le fichier
                if(f.getName().substring(f.getName().length() -5).equals(".java")){
                    files.add(f.getPath());
                }
            }
        }

        return files;

    }
    public SimpleDirectedGraph DrawDendrogramme(DirectedWeightedPseudograph hierarchie){
        int index= 0;
        SimpleDirectedGraph<String, DefaultEdge> dendrogramme =
                new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        for(Object o : hierarchie.vertexSet()){
            dendrogramme.addVertex(o.toString());
        }
        while(hierarchie.vertexSet().size() > 1){
            System.out.println(index);
            for (Object m : hierarchie.vertexSet()) {
                System.out.println(m.toString());
                Set<MyWeightedEdge> edges = hierarchie.outgoingEdgesOf(m.toString());
            }
            String[] ex = ClusterProche(hierarchie);
            if(ex[0] == null){
                break;
            }
            System.out.println(ex[0] + "    " + ex[1]);

            dendrogramme.addVertex(ex[0]+":"+ex[1]);
            dendrogramme.addEdge(ex[0],ex[0]+":"+ex[1]);
            dendrogramme.addEdge(ex[1],ex[0]+":"+ex[1]);
            hierarchie = CreateCluster(hierarchie, ex);
            ++index;

        }
        return dendrogramme;


    }

    public DirectedWeightedPseudograph getGraph() {
        return graph;
    }

    public DirectedWeightedPseudograph<String, MyWeightedEdge> getGraphcluster() {
        return graphcluster;
    }

    public SimpleDirectedGraph<String, MyWeightedEdge> getDendogramme() {
        return dendogramme;
    }
}

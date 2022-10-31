package org.example;


import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;
import org.example.Interface.JavaInterface;
import org.example.Visitor.ClassVisitor;
import org.example.Visitor.MethodeInvocationVisitor;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Parser {
    private String path;

    public static final String jrePath = "/usr/lib/jvm/jrt-fs.jar";
    private DirectedWeightedPseudograph<String, MyWeightedEdge> graph;
    private ASTParser parser;
    public Parser(String path) throws IOException {
        this.path = path;
        ArrayList<String> f = Scan(path);
        graph = Parse(f);
        HierarchieCluster(graph);
    }


    public DirectedWeightedPseudograph Parse(ArrayList<String> f) throws IOException {
        DirectedWeightedPseudograph g =new DirectedWeightedPseudograph<>(MyWeightedEdge.class);
        ClassVisitor classVisitor = new ClassVisitor();

        for (String m: f) {
            CompilationUnit cu = AST(m);
            cu.accept(classVisitor);
        }
            //on parcrourt toutes les classes du fichier
            for(TypeDeclaration classe : classVisitor.getClasses()) {
                HashMap<String,Integer> map = new HashMap<>();
                int nbappel = 0;
                //parcourt les methodes de cette classe
                for (MethodDeclaration methodDeclaration : classe.getMethods()) {
                    //On vérifie si la méthode n'a pas deja été enregistrer par le graph
                    if (!g.containsVertex(classe.getName().toString())) {
                        //On enregistre la méthode dans le graph
                        g.addVertex(classe.getName().toString() );

                    }

                    MethodeInvocationVisitor visitor2 = new MethodeInvocationVisitor();
                    methodDeclaration.accept(visitor2);
                    //On parcourt toutes les méthodeInvocation

                    for (MethodInvocation methodInvocation : visitor2.getMethods()) {
                        ++nbappel;

                        if (methodInvocation.getExpression().resolveTypeBinding() != null) {

                            if (!g.containsVertex(methodInvocation.getExpression().resolveTypeBinding().getName())) {
                                //si la méthode n'a pas deja été enregistrer on le met dans le graph
                                g.addVertex(methodInvocation.getExpression().resolveTypeBinding().getName());
                            }
                            if (!map.containsKey(classe.getName().toString()+":"+ methodInvocation.getExpression().resolveTypeBinding().getName())) {
                                map.put(classe.getName().toString()+":"+ methodInvocation.getExpression().resolveTypeBinding().getName(),1);
                                //g.setEdgeWeight(e, 1);
                            } else {
                                map.put(classe.getName().toString()+":"+ methodInvocation.getExpression().resolveTypeBinding().getName(), map.get(classe.getName().toString()+":"+ methodInvocation.getExpression().resolveTypeBinding().getName())+1);
                                //g.setEdgeWeight(e, ((float)g.getEdgeWeight(e) + 1));
                            }
                        }
                    }
                }
                for (Map.Entry m : map.entrySet()) {
                    int i = (Integer) m.getValue();
                    String arret =(String) m.getKey();
                    String[] arretes = arret.split(":");
                    DefaultWeightedEdge e = (DefaultWeightedEdge)g.addEdge(arretes[0],arretes[1]);
                    g.setEdgeWeight(e, (float)i/nbappel);

                }







            }

        return g;

    }

    public void HierarchieCluster(DirectedWeightedPseudograph graph){

        //graph.getAllEdges();


        ArrayList<Cluster> clusters = new ArrayList<>();
        graph.vertexSet();
        for (Object m : graph.vertexSet()) {
            System.out.println(m.toString());
            Set<MyWeightedEdge> edges = graph.outgoingEdgesOf(m.toString());
            for(MyWeightedEdge e : edges){
                System.out.println("    " + e.toString() + " " + graph.getEdgeTarget(e));
            }
        }

    }

    //initialise l'AST.
    public CompilationUnit AST(String Path) throws IOException {
        parser = ASTParser.newParser(AST.JLS4);
        ASTParser parser = ASTParser.newParser(AST.JLS4); // java +1.6
        parser.setResolveBindings(true);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        parser.setBindingsRecovery(true);

        Map options = JavaCore.getOptions();
        parser.setCompilerOptions(options);

        parser.setUnitName("");

        String[] sources = { "src/" };
        String[] classpath = {jrePath};

        parser.setEnvironment(classpath, sources, new String[] { "UTF-8"}, true);
        parser.setSource(readFileToString(Path).toCharArray());
        return (CompilationUnit) parser.createAST(null);
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


    public DirectedWeightedPseudograph getGraph() {
        return graph;
    }
}

package Exo2;

import Exo1.Visitor.ClassVisitor;
import Exo1.example.Repository.JavaClass;

import Exo2.Visitor.MethodeInvocationVisitor;
import Exo2.Visitor.MethodeVisitor;
import org.eclipse.jdt.core.dom.*;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    String path;
    ArrayList<MethodDeclaration> methodDeclarations ;


    Graph<String, DefaultEdge> graph;
    private ASTParser parser;
    public Parser(String path) throws IOException {
        this.path = path;
        graph = new SimpleGraph<>(DefaultEdge.class);
        methodDeclarations = new ArrayList<>();
        ArrayList<String> f = Scan(path);
        for (String m: f) {
            CompilationUnit cu = AST(m);
            ClassVisitor classVisitor = new ClassVisitor();
            cu.accept(classVisitor);
            //on parcrourt toutes les classes du fichier
            for(TypeDeclaration classe : classVisitor.getClasses()) {
                //parcourt les methodes de cette classe
                for (MethodDeclaration methodDeclaration : classe.getMethods()) {
                    //On vérifie si la méthode n'a pas deja été enregistrer par le graph
                    if (!graph.containsVertex(classe.getName().toString() +":"+ methodDeclaration.getName().toString())) {
                        TypeDeclaration t = (TypeDeclaration) methodDeclaration.getParent();
                        //On enregistre la méthode dans le graph
                        graph.addVertex(classe.getName().toString() +":"+methodDeclaration.getName().toString());
                    }

                    MethodeInvocationVisitor visitor2 = new MethodeInvocationVisitor();
                    methodDeclaration.accept(visitor2);
                    //On parcourt toutes les méthodeInvocation
                    for (MethodInvocation methodInvocation : visitor2.getMethods()) {

                        if (!graph.containsVertex(classe.getName().toString() +":"+methodInvocation.getName().toString())) {
                            //si la méthode n'a pas deja été enregistrer on le met dans le graph
                            graph.addVertex(classe.getName().toString() +":"+methodInvocation.getName().toString());
                        }
                        if (!graph.containsEdge(classe.getName().toString() +":"+methodDeclaration.getName().toString(), classe.getName().toString() +":"+methodInvocation.getName().toString())) {
                            //si le lien entre les 2 méthodes n'existe pas deja on créer le lien entre les 2 méthodes.
                            graph.addEdge(classe.getName().toString() +":"+methodDeclaration.getName().toString(), classe.getName().toString() +":"+methodInvocation.getName().toString());
                        }
                    }
                }
            }
        }
    }

    //initialise l'AST.
    public CompilationUnit AST(String Path) throws IOException {
        parser = ASTParser.newParser(AST.JLS3);
        parser.setSource(readFileToString(Path).toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
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


    public Graph<String, DefaultEdge> getGraph() {
        return graph;
    }
}

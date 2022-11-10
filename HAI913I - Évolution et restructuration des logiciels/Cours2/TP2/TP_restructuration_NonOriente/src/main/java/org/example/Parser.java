package org.example;


import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;
import org.example.Visitor.ClassVisitor;
import org.example.Visitor.MethodeInvocationVisitor;
import org.jgrapht.Graph;
import org.jgrapht.graph.*;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Parser {

    public static final String jrePath = "/usr/lib/jvm/jrt-fs.jar";
    private Graph<String, MyWeightedEdge> graph ;

    private Graph<String, MyWeightedEdge> graphcluster;

    private Graph<String, DefaultEdge> dendogramme;

    private Graph<String, MyWeightedEdge> Graphmodule;
    private ASTParser parser;
    public Parser(String path,int etape,float CP) throws IOException{
        ArrayList<String> f = Scan(path);
        //creation du graph d'appel
        this.graph = Parse(f);
        //Création du graph contenant les différents modules
        this.Graphmodule = GroupeClasse(this.graph, CP);
        //Création du graph avec les différents cluster a une etape
        this.graphcluster = HierarchieCluster(this.graph,etape);
        //création du Dendrogramme du graph
        this.dendogramme = DrawDendrogramme(this.graph);
    }




    public SimpleWeightedGraph<String, MyWeightedEdge> Parse(ArrayList<String> f) throws IOException {
        SimpleWeightedGraph<String, MyWeightedEdge> g =new SimpleWeightedGraph<>(MyWeightedEdge.class);
        ClassVisitor classVisitor = new ClassVisitor();

        //instanciation de toutes les classes du projet
        for (String m: f) {
            CompilationUnit cu = AST(m);
            cu.accept(classVisitor);
        }
        //On va créer une hasmap qui va contenir les combinaison (A:B, 5) symbolisant le nombre d'appel entre la classe A et la classe B.
        HashMap<String,Integer> map = new HashMap<>();
        //map qui enregistre le nombre d'invication par classe
        HashMap<String,Integer> nbinvocation = new HashMap<>();
            //on parcourt toutes les classes du fichier
            for(TypeDeclaration classe : classVisitor.getClasses()) {
                //On compte le d'appel
                int nbappel = 0;
                //parcourt les methodes de cette classe
                for (MethodDeclaration methodDeclaration : classe.getMethods()) {
                    //On vérifie si la classe n'a pas deja été enregistrer par le graph
                    if (!g.containsVertex(classe.getName().toString())) {
                        //On enregistre la classe dans le graph
                        g.addVertex(classe.getName().toString() );

                    }

                    MethodeInvocationVisitor visitor2 = new MethodeInvocationVisitor();
                    methodDeclaration.accept(visitor2);
                    //On parcourt toutes les méthodeInvocation

                    for (MethodInvocation methodInvocation : visitor2.getMethods()) {

                        if(methodInvocation.getExpression() != null){

                        if (methodInvocation.getExpression().resolveTypeBinding() != null && !methodInvocation.getExpression().resolveTypeBinding().getName().equals("")) {
                            ++nbappel;
                            if (!g.containsVertex(methodInvocation.getExpression().resolveTypeBinding().getName().toString())) {
                                //si la classe n'a pas deja été enregistrer on le met dans le graph
                                g.addVertex(methodInvocation.getExpression().resolveTypeBinding().getName().toString());
                            }

                            //On vérifie si le lien n'est pas déjà dans la hashmap
                            if (!map.containsKey(classe.getName().toString()+":"+ methodInvocation.getExpression().resolveTypeBinding().getName().toString()) && !map.containsKey(methodInvocation.getExpression().resolveTypeBinding().getName().toString()+":"+ classe.getName().toString())) {
                                //Si elle y est pas on met le lien dans la hashmap sous forme A:B
                                map.put(classe.getName().toString()+":"+ methodInvocation.getExpression().resolveTypeBinding().getName().toString(),1);


                            } else {
                                //Si le lien existe on incrémente le nombre d'appel invoqué (On vérifie si c'est le lien A:B ou B:A)
                                if(map.containsKey(classe.getName().toString()+":"+ methodInvocation.getExpression().resolveTypeBinding().getName())){
                                    //on incrémente le nombre d'appel de la classe A vers B
                                    map.put(classe.getName().toString()+":"+ methodInvocation.getExpression().resolveTypeBinding().getName(), map.get(classe.getName().toString()+":"+ methodInvocation.getExpression().resolveTypeBinding().getName())+1);
                                }else{
                                    //on incrémente le nombre d'appel de la classe A vers B
                                    map.put(methodInvocation.getExpression().resolveTypeBinding().getName()+":"+classe.getName().toString(), map.get( methodInvocation.getExpression().resolveTypeBinding().getName()+":"+classe.getName().toString() )+1);
                                }
                            }
                        }
                    }
                    }
                }
                //On met le nombre d'appel total dans la hashmap selon une classe
                nbinvocation.put(classe.getName().toString(),nbappel);

            }

        //grace a la hasmap on créer le graph d'appel avec le bon couplage
        for (Map.Entry m : map.entrySet()) {
            int nb = (Integer) m.getValue();
            String arret =(String) m.getKey();
            //On parcours tout les appel enregistrer
            String[] arretes = arret.split(":");
            //Si l'arret n'existe on rentre dans le if pour créer les liens
            if(!g.containsEdge(arretes[0],arretes[1])) {
                /*
                    Si A invoque les methodes de B et B invoque les méthodes de A alors on additionne le nombre d'invocation de A vers B et de B vers A
                    et on divise par le nombre d'invovation total
                   */
                if (nbinvocation.containsKey(arretes[0]) && nbinvocation.containsKey(arretes[1])) {
                    MyWeightedEdge e = (MyWeightedEdge) g.addEdge(arretes[0], arretes[1]);
                    g.setEdgeWeight(e, (double) nb / (nbinvocation.get(arretes[0]) + nbinvocation.get(arretes[1])));
                }
                /*
                Si une classe n'est pas dans nbinvocation cela veut dire que la classe invoqué n'est pas acccésible donc on considère que son
                nombre d'invocation est de 0.
                 */
                else if (nbinvocation.containsKey(arretes[0])) {
                    MyWeightedEdge e = (MyWeightedEdge) g.addEdge(arretes[0], arretes[1]);
                    g.setEdgeWeight(e, (double) nb / nbinvocation.get(arretes[0]));

                } else {
                    MyWeightedEdge e = (MyWeightedEdge) g.addEdge(arretes[0], arretes[1]);
                    g.setEdgeWeight(e, (double) nb / (nbinvocation.get(arretes[1])));
                }
            }
        }
        return g;

    }

    /*
    cette méthode permet de faire une copi d'un graph
     */
    public SimpleWeightedGraph copygraph(Graph<String, MyWeightedEdge> graph){
        SimpleWeightedGraph<String, MyWeightedEdge> g =new SimpleWeightedGraph<>(MyWeightedEdge.class);

        //On copie les noeuds
        for (Object m : graph.vertexSet()) {
            g.addVertex(m.toString());
        }

        //On copie les arrets.
        for (Object m : graph.vertexSet()) {
            Set<MyWeightedEdge> edges = graph.edgesOf(m.toString());
            for(MyWeightedEdge e : edges){
                if(!m.equals(graph.getEdgeTarget(e).toString())){
                    MyWeightedEdge val = (MyWeightedEdge)g.addEdge(m.toString(),graph.getEdgeTarget(e).toString() );
                    g.setEdgeWeight(val, graph.getEdgeWeight(e));
                }
            }


        }
        return g;
    }

    /*
    Cette fonction retourne un couple de noeud avec le plus grand couplage  d'un graph passé en paramètre.
     */
    public String[] ClusterProche(Graph<String, MyWeightedEdge> graph){

        String[] ret = new String[2];
        double valmax = 0;
        //On parcourt tous les sommet et arrets du graph
        for (Object m : graph.vertexSet()) {
            //Pour chaque noeud on parcourt les arret sortant du noeud
            Set<MyWeightedEdge> edges = graph.edgesOf(m.toString());
            for(MyWeightedEdge e : edges){
                //couplage va contenir le couplage entre les 2 noeuds
                double couplage = 0;
                 couplage = graph.getEdgeWeight(e);



                    //le couplage est ensuite égal a la moyenne des 2 arrets
                    //si le couplage est le plus grand enregistrer alors on enregistre le nouveau couple de noeud.
                if(couplage > valmax){

                    ret[0] = graph.getEdgeSource(e).toString();
                    ret[1] = graph.getEdgeTarget(e).toString();
                    valmax = couplage;
                }
            }
        }
        //On retourne le couple de noeud enregistré.
        return ret;

    }

    /*
    Cette fonction prend un graph et deux noeud et va retourner un nouveau graphe avec un noeud contenant les 2 noeuds passé en paramètre.
     */
    public SimpleWeightedGraph CreateCluster(SimpleWeightedGraph<String, MyWeightedEdge> graph, String[] ex){
        //on crée un noeud contenant le nom des 2 classes concaténé d'un :
        SimpleWeightedGraph<String, MyWeightedEdge> g = copygraph(graph);
        g.addVertex(ex[0]+":"+ex[1]);

        //On récupère toutes les arrets entrante du premier noeud pour les mettres au nouveau noeud
        Set<MyWeightedEdge> edgesoutput1 = graph.edgesOf(ex[0]);

        for(MyWeightedEdge e : edgesoutput1){

            //On vérifie que le lien n'a pas déjà été créé par un autre noeud ayant un lien vers le même noeud
            if(!g.containsEdge(g.getEdge(ex[0]+":"+ex[1],g.getEdgeTarget(e)))){
                MyWeightedEdge tampon = (MyWeightedEdge)g.addEdge(ex[0]+":"+ex[1],g.getEdgeTarget(e));
                g.setEdgeWeight(tampon,graph.getEdgeWeight(e));
                //On supprime l'arret
                g.removeEdge(e);
            }else{
                MyWeightedEdge tampon = (MyWeightedEdge)g.getEdge(ex[0]+":"+ex[1],g.getEdgeTarget(e));
                g.setEdgeWeight(tampon, (graph.getEdgeWeight(e) +graph.getEdgeWeight(tampon))/2 );

            }


        }



//On récupère toutes les arrets entrante du deuxième noeud pour les mettres au nouveau noeud
        Set<MyWeightedEdge> edgesoutput2 = graph.edgesOf(ex[1]);
        for(MyWeightedEdge e : edgesoutput2){
            //On vérifie que le lien n'a pas déjà été créé par un autre noeud ayant un lien vers le même noeud
            if(!g.containsEdge(g.getEdge(ex[0]+":"+ex[1],g.getEdgeSource(e)))){
                MyWeightedEdge tampon = (MyWeightedEdge)g.addEdge(ex[0]+":"+ex[1],g.getEdgeSource(e));
                g.setEdgeWeight(tampon, graph.getEdgeWeight(e));
                //On supprime l'arret
                g.removeEdge(e);
            }else{
                MyWeightedEdge tampon = (MyWeightedEdge)g.getEdge(ex[0]+":"+ex[1],g.getEdgeSource(e));
                g.setEdgeWeight(tampon, graph.getEdgeWeight(e) +graph.getEdgeWeight(tampon) /2 );

            }
        }

        //on supprime les anciens noeuds
        g.removeVertex(ex[0]);
        g.removeVertex(ex[1]);

        return g;
    }

    /*
    Cette fonction prend un graph et un entier i et va retourner un graph avec les diférents cluster a une etape i de l'algorithme en utilisant les fonctions écritent précédemment.
     */
    public SimpleWeightedGraph HierarchieCluster(Graph<String, MyWeightedEdge> hierarchie, int i){
        //On enregistre l'étape ou ce situe l'algorithme
        int index = 0;
        //On fait une copie du graph pour pas modifier le graph directement
        SimpleWeightedGraph<String, MyWeightedEdge> graph = copygraph(hierarchie);

        boolean exit = false;
        //tant que le nombre de classe est supérieur a 1 et qu'on a pas passé la i éme étape
        while(graph.vertexSet().size() > 1&& i != index && !exit){
            //On récupère les 2  noeuds les plus couplet
            String[] ex = ClusterProche(graph);
            //si null alors c'est qu'il n'y en a pas de plus couplet donc sortir de la boucle
            if(ex[0] == null){
                break;
            }
            //Recréer le graph avec le nouveay cluster
            graph = CreateCluster(graph, ex);



            //incrémenté l'étape
            ++index;

        }
        return graph;

    }
/*
Cette fonction permet de créer un nouveau noeud contenant a partir du noeud cible symbolisant un cluster et lui enlève la class "cible" et retourne le nouveau noeud (cluster) sans la classe "cible"
 */
public String newNode(String cible,String remove){
    String newnode = "";
    //On parcourt toutes les classes du cluster
    for(String m : cible.split(":")){
        //Si on trouve la classe que l'on veut supprimer on ne l'ajouter pas au nouveau cluster
        if(!m.equals(remove)){
            if(newnode.equals("")){
                newnode = m;
            }else{
                newnode = newnode +":"+m;
            }
        }
    }
    return newnode;
}
/*
Cette fonction utilise la methode précédente pour intégrer le nouveau noeud dans le graph et suprimer le noeud "cible"
 */
public SimpleWeightedGraph replaceVertex(String cible,String remove,Graph<String, MyWeightedEdge> module){


    //On copie le graph pour ne pas modifier le graph original vu que java passe les paramètre par référence et pas par copie
    SimpleWeightedGraph ret = copygraph(module);
    //on créer le nouveau noeud en enlevant la classe a supprimer
    String newnode = newNode(cible,remove);
    //On parcourt tout les clusters du graph
    for(Object o : module.vertexSet()){
        //Si on trouve le cluster visé
        if(cible.equals(o.toString())){
            //On rajoute le nouveau noeud avec la class en moins
            ret.addVertex(newnode);
            //On recréer chaque lien lié au l'ancien noeud vers le nouveau noeud avec les bons poids
            for(Object i : ret.vertexSet()){
                if(ret.getEdge(i.toString(),cible) != null){
                    MyWeightedEdge e = (MyWeightedEdge)ret.addEdge(i.toString(),newnode);
                    ret.setEdgeWeight(e, graph.getEdgeWeight(e));
                    ret.removeEdge(i.toString(),cible);
                }
                if(ret.getEdge(cible,i.toString()) != null){
                    MyWeightedEdge e = (MyWeightedEdge)ret.addEdge(newnode,cible);
                    ret.setEdgeWeight(e, graph.getEdgeWeight(e));
                    ret.removeEdge(cible,i.toString());
                }
            }
            //On supprime l'ancien noeud
            ret.removeVertex(o.toString());
        }
    }
    return ret;
}

    /*
    Cette fonction retourne le couplage moyen d'un module en fonction du nom du module et du graph de couplage passé en paramètre.
     */
    public double moyennecouplage(String module,Graph<String, MyWeightedEdge> graph){
        //On récupères toutes les classes du cluster
        String[] l = module.split(":");
        double total = 0;
        double nbedge = 0;
        //On parcourt tout les classes du cluster afin de voir toutes les liens de chaque classe
        for(String s : l){
            for(String p : l){
                //Pour chaque classe on regarde tous ces liens et on additionne le couplage total et on incrémente le nombre de lien du cluster
                MyWeightedEdge edge = (MyWeightedEdge)this.graph.getEdge(s,p);
                if(edge !=null){
                    ++nbedge;
                    total = total + this.graph.getEdgeWeight(edge);
                }

            }
        }
        //Si il n'y a pas de lien dans le cluster alors le cluster a un couplage de 1.
        if(nbedge == 0){
            return 1;
        }
        else{
            //On fait la moyenne du cluster.
            return total/nbedge;
        }

    }

    /*
    Cette fonction retourne le noeud d'un cluster qui est le moin couplé en fonction du nom du cluster et du graph de coulage.
     */
    public String VertexMustSmall(String module,Graph<String, MyWeightedEdge> graph){
        //On récupère toutes les classes du cluster.
        String[] l = module.split(":");
        //On défini le couplage minimum
        float min = Float.MAX_VALUE;
        String vertex = "";
        //On parcourt tous les classes du cluster.
        for(String s : l){
            float edgecurrent = 0;
            //Pour chaque classe du cluster on voit toutes les liens entrant et sortant et on les additionne
            for(String p : l){
                MyWeightedEdge edge = (MyWeightedEdge)graph.getEdge(s,p);
                if(edge !=null){
                    edgecurrent = edgecurrent + Float.valueOf(edge.toString());
                }
                edge = (MyWeightedEdge)graph.getEdge(p,s);
                if(edge !=null){
                    edgecurrent = edgecurrent + Float.valueOf(edge.toString());
                }

            }

            //Si l'addition de tout ces liens est le plus petit alors on l'enregistre comme la classe ayant le plus faible couplage
            if(edgecurrent < min){
                min = edgecurrent;
                vertex = s;
            }


        }
        return vertex;

    }

    /*
Cette fonction répont à la question 2 de l'exo2 et créer différent module en respectant que le nombre de module ne dépasse pas M/2 ou M est le nombre de classe.
Que chaque classe de chaque module face parti de la même branche du dendogramme.
Et que le nombre moyen de couplage par module soit supérieur a CP.
     */
    public SimpleWeightedGraph GroupeClasse(Graph<String, MyWeightedEdge> hierarchie, float CP) {
        //On fait des copies du graph pour éviter de modifier le graph original
        SimpleWeightedGraph module = HierarchieCluster(hierarchie, 0);
        SimpleWeightedGraph module2 = HierarchieCluster(hierarchie, 0);
        int index = 1;
        while(module.vertexSet().size() > module2.vertexSet().size()/2){
            module = HierarchieCluster(module2, index);
            ++index;
        }
        //Je refais une copie du graph pour éviter de modifier le graph original
        SimpleWeightedGraph ret = copygraph(module);
        //On parcourt tout les clusters du graph
        for(Object o : module.vertexSet()){


            String classe = o.toString();
            //On voit si la moyenne du premier cluster est bien inférieur a CP
            while(moyennecouplage(classe,ret) < CP){
                //Sinon non on recherche le noeud a le moin de couplage avec ces voisin afin d'avoir une moyenne plus haute.
                String noeud = VertexMustSmall(classe,module2);


                //On suprime le noeud du graph
                module2.removeVertex(noeud);
                ret = copygraph(replaceVertex(classe,noeud,ret));


                classe = newNode(classe,noeud);
            }

        }



        //On retourne le graph final
        return ret;


    }

    public void exportGraph(Graph<String, MyWeightedEdge> graph , String filename) throws IOException {



        DOTExporter<String, MyWeightedEdge> exporter = new DOTExporter<String, MyWeightedEdge>();
        exporter.setVertexAttributeProvider((v) -> {
            Map<String, Attribute> map = new LinkedHashMap<String, Attribute>();
            map.put("label", DefaultAttribute.createAttribute(v.toString()));
            return map;
        });
        exporter.setEdgeAttributeProvider((e) -> {
            Map<String, Attribute> map = new LinkedHashMap<String, Attribute>();
            map.put("weight", DefaultAttribute.createAttribute(graph.getEdgeWeight(e)));
            map.put("label", DefaultAttribute.createAttribute(graph.getEdgeWeight(e)));
            return map;
        });
        Writer writer = new StringWriter();
        exporter.exportGraph(graph, writer);
        MutableGraph g = new guru.nidi.graphviz.parse.Parser().read(writer.toString());
        Graphviz.fromGraph(g).height(1000).render(Format.PNG).toFile(new File(filename +".png"));


    }

    public void exportDendrogramme(Graph<String, DefaultEdge> graph , String filename) throws IOException {



        DOTExporter<String, DefaultEdge> exporter = new DOTExporter<String, DefaultEdge>();
        exporter.setVertexAttributeProvider((v) -> {
            Map<String, Attribute> map = new LinkedHashMap<String, Attribute>();
            map.put("label", DefaultAttribute.createAttribute(v.toString()));
            return map;
        });
        Writer writer = new StringWriter();
        exporter.exportGraph(graph, writer);
        MutableGraph g = new guru.nidi.graphviz.parse.Parser().read(writer.toString());
        Graphviz.fromGraph(g).height(1000).render(Format.PNG).toFile(new File(filename +".png"));


    }
    public SimpleDirectedGraph DrawDendrogramme(Graph<String, MyWeightedEdge> hierarchie){
        //je crée le graph dendogramme
        SimpleDirectedGraph<String, EmptyEdge> dendrogramme =
                new SimpleDirectedGraph<String, EmptyEdge>(EmptyEdge.class);

        SimpleWeightedGraph<String, MyWeightedEdge> graph = copygraph(hierarchie);
        //je met chaque noeud du graph dans le dendogramm (ce sera la base de mon dendogramme)
        for(Object o : graph.vertexSet()){
            dendrogramme.addVertex(o.toString());
        }
        //ensuite j'exécute l'algorithme de clustering
        while(graph.vertexSet().size() > 1){
            for (Object m : graph.vertexSet()) {
                Set<MyWeightedEdge> edges = graph.outgoingEdgesOf(m.toString());
            }
            String[] ex = ClusterProche(graph);
            if(ex[0] == null){
                break;
            }
            //et pour chaque cluster je le met dans mon dendogramm
            dendrogramme.addVertex(ex[0]+":"+ex[1]);
            dendrogramme.addEdge(ex[0],ex[0]+":"+ex[1]);
            dendrogramme.addEdge(ex[1],ex[0]+":"+ex[1]);
            graph = CreateCluster(graph, ex);

        }
        return dendrogramme;


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


    public Graph<String, MyWeightedEdge> getGraph() {
        return graph;
    }

    public Graph<String, MyWeightedEdge> getGraphcluster() {
        return graphcluster;
    }

    public Graph<String, MyWeightedEdge> getGraphmodule() {
        return Graphmodule;
    }

    public Graph<String, DefaultEdge> getDendogramme() {
        return dendogramme;
    }
}

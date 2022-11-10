package org.example;


import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.*;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;
import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.filter.TypeFilter;


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class Parser {
    public static final String jrePath = "/usr/lib/jvm/jrt-fs.jar";
    private DirectedWeightedPseudograph<String, MyWeightedEdge> graph;

    private DirectedWeightedPseudograph<String, MyWeightedEdge> graphcluster;

    private DirectedWeightedPseudograph<String, MyWeightedEdge> Graphmodule;
    private SimpleDirectedGraph<String, DefaultEdge> dendogramme;
    private Launcher parser;
    public Parser(String path,int etape,float CP) throws IOException, ExecutionException, InterruptedException {
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
    public CtModel AST(String Path)  {
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
                for(Object method : type.getMethods()){
                    if (!g.containsVertex(type.getSimpleName())) {
                        //On enregistre la méthode dans le graph
                        g.addVertex(type.getSimpleName());

                    }
                    CtMethod p = (CtMethod) method;
                    p.filterChildren(new TypeFilter(CtInvocation.class)).forEach(inv->{

                        nbappel.incrementAndGet();
                        CtInvocation<?> invocation = (CtInvocation<?>) inv;
                        if(invocation.getExecutable().getDeclaringType() != null) {
                            if (!g.containsVertex(invocation.getExecutable().getDeclaringType().getSimpleName())) {
                                //si la méthode n'a pas deja été enregistrer on le met dans le graph
                                g.addVertex(invocation.getExecutable().getDeclaringType().getSimpleName());
                            }
                            if (!map.containsKey(type.getSimpleName() + ":" + invocation.getExecutable().getDeclaringType().getSimpleName())) {
                                map.put(type.getSimpleName() + ":" + invocation.getExecutable().getDeclaringType().getSimpleName(), 1);
                                //g.setEdgeWeight(e, 1);
                            } else {
                                map.put(type.getSimpleName() + ":" + invocation.getExecutable().getDeclaringType().getSimpleName(), map.get(type.getSimpleName() + ":" + invocation.getExecutable().getDeclaringType().getSimpleName()) + 1);
                                //g.setEdgeWeight(e, ((float)g.getEdgeWeight(e) + 1));
                            }
                        }
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


    /*
    cette méthode permet de faire une copi d'un graph
     */
    public DirectedWeightedPseudograph copygraph(DirectedWeightedPseudograph<String, MyWeightedEdge> graph){
        DirectedWeightedPseudograph g =new DirectedWeightedPseudograph<>(MyWeightedEdge.class);

        //On copie les noeuds
        for (Object m : graph.vertexSet()) {
            g.addVertex(m.toString());
        }

        //On copie les arrets.
        for (Object m : graph.vertexSet()) {
            Set<MyWeightedEdge> edges = graph.outgoingEdgesOf(m.toString());
            for(MyWeightedEdge e : edges){
                DefaultWeightedEdge val = (DefaultWeightedEdge)g.addEdge(m.toString(),graph.getEdgeTarget(e).toString() );
                g.setEdgeWeight(val, graph.getEdgeWeight(e));
            }


        }
        return g;
    }

    /*
    Cette fonction retourne un couple de noeud avec le plus grand couplage  d'un graph passé en paramètre.
     */
    public String[] ClusterProche(DirectedWeightedPseudograph<String, MyWeightedEdge> graph){

        String[] ret = new String[2];
        double valmax = 0;
        //On parcourt tous les sommet et arrets du graph
        for (Object m : graph.vertexSet()) {
            //Pour chaque noeud on parcourt les arret sortant du noeud
            Set<MyWeightedEdge> edges = graph.outgoingEdgesOf(m.toString());
            for(MyWeightedEdge e : edges){
                //couplage va contenir le couplage entre les 2 noeuds
                double couplage = 0;
                //valneoud va contenir la valeur de l'arret sortante
                double valneoud = graph.getEdgeWeight(e);
                //valneoud va contenir la valeur de l'arret entrante
                double valnoeudopposer = 0;

                //On parcourt ensuite les noeuds entrant
                Set<MyWeightedEdge> edgesoutput1 = graph.incomingEdgesOf(graph.getEdgeTarget(e).toString());
                for(MyWeightedEdge in : edgesoutput1){
                    //Si on trouve le meme sommet que pour le noeud sortant alors on enregistre ca valeur sinon elle reste a 0.
                    if(graph.getEdgeTarget(in).toString().equals(m.toString())){
                        valnoeudopposer =graph.getEdgeWeight(in) ;
                    }

                }
                //le couplage est ensuite égal a la moyenne des 2 arrets
                couplage = (valneoud +valnoeudopposer)/2;
                //si le coupla est le plus grand enregistrer alors on enregistre le nouveau couple de noeud.
                if(couplage > valmax){
                    ret[0] = m.toString();
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
    public DirectedWeightedPseudograph CreateCluster(DirectedWeightedPseudograph graph, String[] ex){
        //on crée un noeud contenant le nom des 2 classes concaténé d'un :
        graph.addVertex(ex[0]+":"+ex[1]);


        //On récupère toutes les arrets entrante du premier noeud pour les mettres au nouveau noeud
        Set<MyWeightedEdge> edgesoutput1 = graph.outgoingEdgesOf(ex[0]);
        for(MyWeightedEdge e : edgesoutput1){
            //On vérifie que le lien n'a pas déjà été créé par un autre noeud ayant un lien vers le même noeud
            if(!graph.containsEdge(graph.addEdge(ex[0]+":"+ex[1],graph.getEdgeTarget(e)))){
                DefaultWeightedEdge tampon = (DefaultWeightedEdge)graph.addEdge(ex[0]+":"+ex[1],graph.getEdgeTarget(e));
                graph.setEdgeWeight(tampon, graph.getEdgeWeight(e));
                //On supprime l'arret
                graph.removeEdge(e);
            }else{
                DefaultWeightedEdge arret = (DefaultWeightedEdge)graph.getEdge(ex[0]+":"+ex[1],graph.getEdgeTarget(e));
                //Si les 2 noeuds ont un lien vers le même noeud alors on fait la moyenne des 2 couplages
                graph.setEdgeWeight(arret, ( graph.getEdgeWeight(arret)+graph.getEdgeWeight(e))/2 );
            }
        }
//On récupère toutes les arrets entrante du deuxième noeud pour les mettres au nouveau noeud
        Set<MyWeightedEdge> edgesoutput2 = graph.outgoingEdgesOf(ex[1]);
        for(MyWeightedEdge e : edgesoutput2){
            //On vérifie que le lien n'a pas déjà été créé par un autre noeud ayant un lien vers le même noeud
            if(!graph.containsEdge(graph.addEdge(ex[0]+":"+ex[1],graph.getEdgeTarget(e)))){
                DefaultWeightedEdge tampon = (DefaultWeightedEdge)graph.addEdge(ex[0]+":"+ex[1],graph.getEdgeTarget(e));
                graph.setEdgeWeight(tampon,graph.getEdgeWeight(e));
                //On supprime l'arret
                graph.removeEdge(e);
            }else{
                DefaultWeightedEdge arret = (DefaultWeightedEdge)graph.getEdge(ex[0]+":"+ex[1],graph.getEdgeTarget(e));
                //Si les 2 noeuds ont un lien vers le même noeud alors on fait la moyenne des 2 couplages
                graph.setEdgeWeight(arret, (graph.getEdgeWeight(arret)  +graph.getEdgeWeight(e))/2 );
            }
        }


        //On récupère toutes les arrets sortante du premier noeud pour les mettres au nouveau noeud
        Set<MyWeightedEdge> edgesinput1 = graph.incomingEdgesOf(ex[0]);
        for(MyWeightedEdge e : edgesinput1){
            //On vérifie que le lien n'a pas déjà été créé par un autre noeud ayant un lien vers le même noeud
            if(!graph.containsEdge(graph.addEdge(graph.getEdgeTarget(e),ex[0]+":"+ex[1]))){
                DefaultWeightedEdge tampon = (DefaultWeightedEdge)graph.addEdge(graph.getEdgeTarget(e),ex[0]+":"+ex[1]);
                graph.setEdgeWeight(tampon, graph.getEdgeWeight(e));
                //On supprime l'arret
                graph.removeEdge(e);
            }else{
                DefaultWeightedEdge arret = (DefaultWeightedEdge)graph.getEdge(graph.getEdgeTarget(e),ex[0]+":"+ex[1]);
                //Si les 2 noeuds ont un lien vers le même noeud alors on fait la moyenne des 2 couplages
                graph.setEdgeWeight(arret, (graph.getEdgeWeight(arret)  +graph.getEdgeWeight(e))/2 );
            }
        }


        //On récupère toutes les arrets sortante du deuxième noeud pour les mettres au nouveau noeud
        Set<MyWeightedEdge> edgesinput2 = graph.incomingEdgesOf(ex[1]);
        for(MyWeightedEdge e : edgesinput2) {
            //On vérifie que le lien n'a pas déjà été créé par un autre noeud ayant un lien vers le même noeud
            if(!graph.containsEdge(graph.addEdge(graph.getEdgeTarget(e),ex[0]+":"+ex[1]))){
                DefaultWeightedEdge tampon = (DefaultWeightedEdge)graph.addEdge(graph.getEdgeTarget(e),ex[0]+":"+ex[1]);
                graph.setEdgeWeight(tampon, Float.valueOf(e.toString()));
                //On supprime l'arret
                graph.removeEdge(e);
            }else{
                DefaultWeightedEdge arret = (DefaultWeightedEdge)graph.getEdge(graph.getEdgeTarget(e),ex[0]+":"+ex[1]);
                graph.setEdgeWeight(arret, (graph.getEdgeWeight(arret) +graph.getEdgeWeight(e))/2 );
            }
        }

        //on supprime les anciens noeuds
        graph.removeVertex(ex[0]);
        graph.removeVertex(ex[1]);

        return graph;
    }

    /*
    Cette fonction prend un graph et un entier i et va retourner un graph avec les diférents cluster a une etape i de l'algorithme en utilisant les fonctions écritent précédemment.
     */
    public DirectedWeightedPseudograph HierarchieCluster(DirectedWeightedPseudograph hierarchie, int i){
        //On enregistre l'étape ou ce situe l'algorithme
        int index = 0;
        //On fait une copie du graph pour pas modifier le graph directement
        DirectedWeightedPseudograph<String, MyWeightedEdge> graph = copygraph(hierarchie);

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
    public DirectedWeightedPseudograph replaceVertex(String cible,String remove,DirectedWeightedPseudograph module){


        //On copie le graph pour ne pas modifier le graph original vu que java passe les paramètre par référence et pas par copie
        DirectedWeightedPseudograph ret = copygraph(module);
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
                        ret.setEdgeWeight(e, graph.getEdgeWeight(e) );
                        ret.removeEdge(i.toString(),cible);
                    }
                    if(ret.getEdge(cible,i.toString()) != null){
                        MyWeightedEdge e = (MyWeightedEdge)ret.addEdge(newnode,cible);
                        ret.setEdgeWeight(e, (graph.getEdgeWeight(e)));
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
    public double moyennecouplage(String module,DirectedWeightedPseudograph graph){
        //On récupères toutes les classes du cluster
        String[] l = module.split(":");
        double total = 0;
        double nbedge = 0;
        //On parcourt tout les classes du cluster afin de voir toutes les liens de chaque classe
        for(String s : l){
            for(String p : l){
                //Pour chaque classe on regarde tous ces liens et on additionne le couplage total et on incrémente le nombre de lien du cluster
                MyWeightedEdge edge = (MyWeightedEdge)graph.getEdge(s,p);
                if(edge !=null){
                    ++nbedge;
                    total = total + graph.getEdgeWeight(edge);
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
    public String VertexMustSmall(String module,DirectedWeightedPseudograph graph){
        //On récupère toutes les classes du cluster.
        String[] l = module.split(":");
        //On défini le couplage minimum
        double min = Float.MAX_VALUE;
        String vertex = "";
        //On parcourt tous les classes du cluster.
        for(String s : l){
            double edgecurrent = 0;
            //Pour chaque classe du cluster on vois toutes les liens entrant et sortant et on les additionne
            for(String p : l){
                DefaultWeightedEdge edge = (DefaultWeightedEdge)graph.getEdge(s,p);
                if(edge !=null){
                    edgecurrent = edgecurrent + graph.getEdgeWeight(edge);
                }
                edge = (DefaultWeightedEdge)graph.getEdge(p,s);
                if(edge !=null){
                    edgecurrent = edgecurrent + graph.getEdgeWeight(edge);
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
    public DirectedWeightedPseudograph GroupeClasse(DirectedWeightedPseudograph hierarchie, float CP) {
        //On fait des copies du graph pour éviter de modifier le graph original
        DirectedWeightedPseudograph module = HierarchieCluster(hierarchie, 0);
        DirectedWeightedPseudograph module2 = HierarchieCluster(hierarchie, 0);
        int index = 1;
        while(module.vertexSet().size() > module2.vertexSet().size()/2){
            module = HierarchieCluster(module2, index);
            ++index;
        }
        //Je refais une copie du graph pour éviter de modifier le graph original
        DirectedWeightedPseudograph ret = copygraph(module);
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
        SimpleDirectedGraph<String, EmptyEdge> dendrogramme =
                new SimpleDirectedGraph<String, EmptyEdge>(EmptyEdge.class);

        DirectedWeightedPseudograph<String, MyWeightedEdge> graph = copygraph(hierarchie);
        for(Object o : graph.vertexSet()){
            dendrogramme.addVertex(o.toString());
        }
        while(graph.vertexSet().size() > 1){
            for (Object m : graph.vertexSet()) {
                Set<MyWeightedEdge> edges = graph.outgoingEdgesOf(m.toString());
            }
            String[] ex = ClusterProche(graph);
            if(ex[0] == null){
                break;
            }

            dendrogramme.addVertex(ex[0]+":"+ex[1]);
            dendrogramme.addEdge(ex[0],ex[0]+":"+ex[1]);
            dendrogramme.addEdge(ex[1],ex[0]+":"+ex[1]);
            graph = CreateCluster(graph, ex);

        }
        return dendrogramme;


    }

    public DirectedWeightedPseudograph getGraph() {
        return graph;
    }

    public DirectedWeightedPseudograph<String, MyWeightedEdge> getGraphcluster() {
        return graphcluster;
    }

    public DirectedWeightedPseudograph<String, MyWeightedEdge> getGraphmodule() {
        return Graphmodule;
    }
    public SimpleDirectedGraph<String, DefaultEdge> getDendogramme() {
        return dendogramme;
    }
}

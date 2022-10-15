package Exo1.example;

import Exo1.example.Repository.JavaFichier;
import Exo1.Visitor.ClassVisitor;
import Exo1.Visitor.MethodeVisitor;
import Exo1.Visitor.PackageVisitor;
import org.eclipse.jdt.core.dom.*;
import Exo1.example.Repository.JavaAttribut;
import Exo1.example.Repository.JavaClass;
import Exo1.example.Repository.JavaMethode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private ASTParser parser;
    private CompilationUnit cu;

    ArrayList<JavaFichier> javaFichier = new ArrayList<>();

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

    //initialise l'AST.
    public CompilationUnit AST(String Path) throws IOException {
        parser = ASTParser.newParser(AST.JLS3);
        parser.setSource(readFileToString(Path).toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        return (CompilationUnit) parser.createAST(null);
    }

    //parcrourt récursivement le projet
    public ArrayList<String> Scan(String Path){

        ArrayList<String> files = new ArrayList<>();
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
            {
                //si le nom du fichier ce termine par ".java" alors enregistrer le fichier
                if(f.getName().substring(f.getName().length() -5).equals(".java")){
                    files.add(f.getPath());
                }
            }
        }

        return files;

    }

    public void AnalyseAST(String path) throws IOException {

        ArrayList<String> f = Scan(path);
        //On parcrourt tous les liens scanner par la fonction d'avant
        for (String m: f) {
            String Nom_class ="";
            ArrayList<JavaClass> classes = new ArrayList<>();

            //On récupère l'AST en fonction du path passé en paramètre
            cu = AST(m);

            //On scan toutes les classes du fichier
            ClassVisitor classVisitor = new ClassVisitor();
            cu.accept(classVisitor);

            //On scan tous les packages du fichier
            PackageVisitor packageVisitor = new PackageVisitor();
            cu.accept(packageVisitor);

            //On parcrourt toutes les classes et interface récupèrer
            for(TypeDeclaration c:classVisitor.getClasses()) {
                //on ne stock que les TypeDeclaration qui ne sont pas des interfaces
                if (!c.isInterface()) {
                    ArrayList<JavaAttribut> attributs = new ArrayList<>();
                    ArrayList<JavaMethode> methodes = new ArrayList<>();
                    Nom_class = c.getName().toString();

                    //On parcrourt tous les attributs de la classe et on les stock dans un JavaAttribut
                    for (FieldDeclaration field : c.getFields()) {
                        for (Object me : field.fragments()) {
                            JavaAttribut attribut = new JavaAttribut(me.toString());
                            attributs.add(attribut);
                        }
                    }


                    //on parcourt toutes les méthodes de la classe et on les stock dans une ArrayList de javaMethode
                    for(MethodDeclaration methodedeclaration : c.getMethods()){
                        int startLineNum = cu.getLineNumber(methodedeclaration.getStartPosition());
                        int endLineNum = cu.getLineNumber(methodedeclaration.getStartPosition() + methodedeclaration.getLength());
                        int nbligne = endLineNum - startLineNum;
                        JavaMethode methode = new JavaMethode(methodedeclaration.getName().toString(), nbligne, methodedeclaration.parameters().size());
                        methodes.add(methode);
                    }


                    ArrayList<String> Packages = new ArrayList<>();
                    for(PackageDeclaration p:packageVisitor.getPackage()) {
                        Packages.add(p.getName().toString());
                     }

                    //on ajoute la classe dans l'ArrayList de JavaClass
                    JavaClass j = new JavaClass(Nom_class,methodes,attributs,Packages);
                    classes.add(j);
                }
            }
            int end = cu.getLength()-1;
            //et on rajoute toutes les classes trouvé dans le fichier dans JavaFichier + la taille du fichier.
             javaFichier.add(new JavaFichier(classes,cu.getLineNumber(end)));
        }

    }


    //On parcourt tout les javaFichier en additionnant leur nombre de classes
    public int nbClasses(){
        int nbclasse = 0;
        for(JavaFichier jf :javaFichier){
            nbclasse = nbclasse+ jf.getClasses().size();
        }
        return nbclasse;
    }


    //On additionne le nombre de ligne de chaque JavaFichier
    public int nbLigneApplication(){
        int nbligne = 0;
        for (JavaFichier j:javaFichier) {
            nbligne = nbligne + j.getNblignes();
        }
        return nbligne;

    }
    public float nbMoyenLigneMethode(){
        float nbtotalLigne = 0;
        for(JavaFichier jf : javaFichier){
            for (JavaClass jc: jf.getClasses()) {
                for (JavaMethode m: jc.getMethodes()) {
                    nbtotalLigne = nbtotalLigne + m.getNbligne();
                }
            }
        }
        return nbtotalLigne/nbMethode();
    }

    public ArrayList<JavaMethode> AllMethode(){
        ArrayList<JavaMethode> ret = new ArrayList<>();
        for(JavaFichier jf : javaFichier){
            for (JavaClass jc: jf.getClasses()) {
                for (JavaMethode m: jc.getMethodes()) {
                    ret.add(m);
                }

            }
        }
        return ret;
    }

    public ArrayList<JavaMethode> methode10ligne(){
        int dixpourcent = (int)(0.1* nbClasses()) +1;
        ArrayList<JavaMethode> methodes1 = this.AllMethode();
        ArrayList<JavaMethode> methodes2 = new ArrayList<>();

        //On créé une ArrayList de méthodes qu'on va ensuite trié.
        methodes2.add(methodes1.get(0));
        for (int i = 1; i < methodes1.size();++i) {
            for(int j = 0; j < methodes2.size();++j){
                if(methodes1.get(i).getNbligne() < methodes2.get(j).getNbligne()){
                    methodes2.add(j,methodes1.get(i));
                    break;
                }
            }
        }

        ArrayList<JavaMethode> ret = new ArrayList<>();
        //On récupère ensuite les 10% dernier Objet de l'ArrayList
        for(int i = methodes2.size(); i >methodes2.size() -dixpourcent ; --i){
            ret.add(methodes2.get(i-1));
        }

        return ret;
    }
    public ArrayList<JavaClass> Classe10methode(){
        int dixpourcent = (int)(0.1* nbClasses())+1 ;
        ArrayList<JavaClass> classes1 = new ArrayList<>();

        //On trie toutes les classes qui contiennent le plus de méthodes
        for(JavaFichier jf : javaFichier) {
            for (int i = 0; i < jf.getClasses().size(); ++i) {
                    if (classes1.size() == 0 && jf.getClasses().size() != 0) {
                        classes1.add(jf.getClasses().get(i));
                    } else {

                        for (int j = 0; j < classes1.size(); ++j) {
                            if (jf.getClasses().get(i).getMethodes().size() < classes1.get(j).getMethodes().size()) {

                                classes1.add(j, jf.getClasses().get(i));
                                break;


                            }
                        }

                    }
                }
        }


        //On récupère ensuite les 10% dernières objet de l'ArrayList
        ArrayList<JavaClass> ret = new ArrayList<>();
        for(int i = classes1.size(); i > (classes1.size()) -(dixpourcent) ; --i){
            ret.add(classes1.get(i-1));
        }

        return ret;
    }
    public int nbMethode(){
        int nbmethode = 0;
        for(JavaFichier jf : javaFichier){
            for (JavaClass j:jf.getClasses()) {
                nbmethode = nbmethode+ j.getMethodes().size();
            }
        }
        return nbmethode;
    }

    public int ParametreMax(){
        int maxParamètre = 0;
        for(JavaFichier jf : javaFichier){
            for (JavaClass j:jf.getClasses()) {
                for (JavaMethode m: j.getMethodes()) {
                    if(maxParamètre < m.getNbparamètre()){
                        maxParamètre = m.getNbparamètre();
                    }

                }
            }
        }
        return maxParamètre;
    }

    public int nbPackage(){

        ArrayList<String> packag = new ArrayList<>();
        for(JavaFichier jf : javaFichier){
            for (JavaClass j:jf.getClasses()) {
                for (String packages : j.getPackage()) {


                    boolean boolpackag = true;
                    if (packag.size() == 0) {
                        packag.add(packages);
                    } else {
                        for (String p : packag) {
                            if (p != null) {
                                if (p.equals(packages)) {
                                    boolpackag = false;
                                }
                            }

                        }
                        if (boolpackag) {
                            packag.add(packages);
                        }
                    }


                }
            }

        }
        return packag.size();




    }

    public float nbMoyenMetode(){
        return (float)nbMethode() / nbClasses();
    }

    public float MoyenAttribut(){
        float nbattribut = 0;
        for(JavaFichier jf : javaFichier){
            for (JavaClass j:jf.getClasses()) {
                nbattribut =nbattribut + j.getAttributs().size();


            }
        }
        return nbattribut /(float) nbClasses();
    }


    public ArrayList<JavaClass> Classe10Attribut(){
        int dixpourcent = (int)(0.1* nbClasses())+1;
        ArrayList<JavaClass> classes1 = new ArrayList<>();

        //On trie les classes par nombre s'attribut
        for(JavaFichier jf : javaFichier){
            for (int i = 0; i < jf.getClasses().size();++i) {
                if(classes1.size() ==0 && jf.getClasses().size() !=0){
                    classes1.add(jf.getClasses().get(i));
                }
                else{
                    for(int j = 0;j< classes1.size() ;++j){
                        if(jf.getClasses().get(i).getAttributs().size() < classes1.get(j).getAttributs().size()){
                            classes1.add(j,jf.getClasses().get(i));
                            break;
                        }
                    }
                }

            }
        }


        //On récupère les 10% dernier objet de l'ArrayList
        ArrayList<JavaClass> ret = new ArrayList<>();
        for(int i = classes1.size(); i > (classes1.size()) -(dixpourcent) ; --i){
            ret.add(classes1.get(i-1));
        }

        return ret;
    }

    public ArrayList<JavaClass> Classe10Attribut10methode(){
        ArrayList<JavaClass> methodes = Classe10methode();
        ArrayList<JavaClass> Attributs = Classe10Attribut();
        ArrayList<JavaClass> ret = new ArrayList<>();
        for (JavaClass i: methodes) {
            for (JavaClass j:Attributs) {
                if(i.getName().equals(j.getName())){
                    ret.add(i);
                }

            }

        }
        return ret;
    }


    public ArrayList<JavaClass> Xmethodes(int X){
        ArrayList<JavaClass> ret =new ArrayList<>();
        for(JavaFichier jf : javaFichier){
            for (JavaClass j: jf.getClasses()) {
                if(j.getMethodes().size() > X){
                    ret.add(j);
                }

            }
        }
        return ret;
    }


}

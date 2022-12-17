package org.example;

import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.MavenLauncher.SOURCE_TYPE;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        Launcher spoon = configureSpoon();
        spoon.run();

    }
    public static Launcher configureSpoon() throws IOException {

        String source = "C:\\Users\\matth\\OneDrive\\Bureau\\Github\\Master2\\HAI913I - Évolution et restructuration des logiciels\\Cours3\\TP\\TP3-Logging";
        String input = "C:\\Users\\matth\\OneDrive\\Bureau\\Github\\Master2\\HAI913I - Évolution et restructuration des logiciels\\Cours3\\TP\\OutPut";
        Launcher spoon =new Launcher();
        spoon.addInputResource(source+"\\src");



        File dossier = new File(input+"\\src");
        dossier.mkdir();
        dossier = new File(input+"\\src\\main");
        dossier.mkdir();
        dossier = new File(input+"\\src\\main\\java");
        dossier.mkdir();
        File src = new File(source+"\\target");
        File dest = new File(input+"\\target");
        copy(src, dest);
         src = new File(source+"\\pom.xml");
         dest = new File(input+"\\pom.xml");
        copy(src, dest);
        src = new File(source+"\\src\\main\\resources");
        dest = new File(input+"\\src\\main\\resources");
        copy(src, dest);


        spoon.setSourceOutputDirectory("C:\\Users\\matth\\OneDrive\\Bureau\\Github\\Master2\\HAI913I - Évolution et restructuration des logiciels\\Cours3\\TP\\OutPut\\src\\main\\java");
        spoon.getEnvironment().setAutoImports(true);

        ClassLoggin classLoggin = new ClassLoggin();


        spoon.addProcessor(classLoggin);



        return spoon;

    }

    public static void copy(File src, File dest) throws IOException{

        if(src.isDirectory()){
            //si le répertoire n'existe pas, créez-le
            if(!dest.exists()){
                dest.mkdir();
                System.out.println("Dossier "+ src + "  > " + dest);
            }
            //lister le contenu du répertoire
            String files[] = src.list();

            for (String f : files) {
                //construire la structure des fichiers src et dest
                File srcF = new File(src, f);
                File destF = new File(dest, f);
                //copie récursive
                copy(srcF, destF);
            }
        }else{
            //si src est un fichier, copiez-le.
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];
            int length;
            //copier le contenu du fichier
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
            System.out.println("Fichier " + src + " > " + dest);
        }
    }

}
package org.example;

import org.apache.commons.cli.*;
import spoon.Launcher;

import java.io.*;

public class Main {
    static String source = "";
    static String output = "";


    public static void main(String[] args) throws IOException, ParseException {
        processArguments(args);
        Launcher spoon = configureSpoon();
        spoon.run();

    }
    public static Launcher configureSpoon() throws IOException {

        Launcher spoon =new Launcher();
        spoon.addInputResource(source+"\\src\\main\\java");



        File dossier = new File(output+"\\src");
        dossier.mkdir();
        dossier = new File(output+"\\src\\main");
        dossier.mkdir();
        dossier = new File(output+"\\src\\main\\java");
        dossier.mkdir();
        File src = new File(source+"\\target");
        System.out.println(source+"\\target");
        File dest = new File(output+"\\target");
        copy(src, dest);
         src = new File(source+"\\pom.xml");
         dest = new File(output+"\\pom.xml");
        copy(src, dest);
        src = new File(source+"\\src\\main\\resources");
        dest = new File(output+"\\src\\main\\resources");
        copy(src, dest);


        spoon.setSourceOutputDirectory(output + "\\src\\main\\java");
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

    private static void processArguments(String[] args) throws ParseException {
        final Options options = configParameters();
        final CommandLineParser parser = new DefaultParser();
        final CommandLine line = parser.parse(options, args);


        source = line.getOptionValue("sources-input", ".");
        output = line.getOptionValue("sources-output");
    }


    private static Options configParameters() {


        final Option sourcesOption = Option.builder("input").longOpt("sources-input").hasArg(true)
                .argName("/chemin/vers/un/dossier/")
                .desc("Le chemin doit pointer a la racine du projet qui contiendra les fichiers sources Java (.java) a analysé.")
                .required(true).build();

        final Option outputOption = Option.builder("output").longOpt("sources-output").hasArg(true)
                .argName("/chemin/vers/un/dossier/")
                .desc("Le chemin doit pointer vers le dossier de sortie qui contiendra les fichiers sources Java (.java) voulu.")
                .required(true).build();
        final Options options = new Options();
        options.addOption(sourcesOption);
        options.addOption(outputOption);

        return options;
    }


}
package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static String queryFile = "src/main/resources/requête";

    public static void main(String[] args) throws IOException {
        System.out.println("Jena parsing data.");
        Jena.parseData();



        ArrayList<String> queries = new ArrayList<>();

        int requettotal = 0;
        int requetzero = 0;
        File dir = new File(queryFile);
        File[] liste = dir.listFiles();


        for (File item : liste) {
            if (item.isFile()) {
                queries.addAll(parseQueries(item.getPath()));
            }
        }

        for (String query : queries) {
            ++requettotal;
            List<String> jenaResult = Jena.processAQuery(query);
            if (jenaResult.size() == 0) {
                ++requetzero;
            }

        }
        System.out.println("resultat = " + requetzero + "/" + requettotal);

    }

    public static ArrayList<String> parseQueries(String path) throws IOException {
        ArrayList<String> queries = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        try (Stream<String> lineStream = Files.lines(Paths.get(path))) {
            Iterator<String> lineIterator = lineStream.iterator();
            StringBuilder queryString = new StringBuilder();

            while (lineIterator.hasNext()) {
                String line = lineIterator.next();
                queryString.append(line);

                if (line.trim().endsWith("}")) {
                    queries.add(queryString.toString());
                    queryString.setLength(0);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        return queries;
    }
}
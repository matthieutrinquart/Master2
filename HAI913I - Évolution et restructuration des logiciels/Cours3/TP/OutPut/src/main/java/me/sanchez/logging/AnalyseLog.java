package me.sanchez.logging;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class AnalyseLog {
    FileReader fileprice = new FileReader("pricelog.log");

    FileReader fileWrite = new FileReader("readlog.log");

    FileReader fileRead = new FileReader("writelog.log");

    List<JSONObject> jsonread = new ArrayList<>();

    List<JSONObject> jsonwrite = new ArrayList<>();

    List<JSONObject> jsonprice = new ArrayList<>();

    public AnalyseLog() throws IOException, ParseException {
        String line;
        BufferedReader br = new BufferedReader(fileprice);
        while ((line = br.readLine()) != null) {
            JSONParser parser = new JSONParser();
            JSONObject json = ((JSONObject) (parser.parse(line)));
            jsonprice.add(json);
        } 
        line = "";
        br = new BufferedReader(fileWrite);
        while ((line = br.readLine()) != null) {
            JSONParser parser = new JSONParser();
            JSONObject json = ((JSONObject) (parser.parse(line)));
            jsonwrite.add(json);
        } 
        line = "";
        br = new BufferedReader(fileRead);
        while ((line = br.readLine()) != null) {
            JSONParser parser = new JSONParser();
            JSONObject json = ((JSONObject) (parser.parse(line)));
            jsonread.add(json);
        } 
    }

    public JSONArray ProfilReader() {
        Map<JSONObject, Integer> profilereader = new HashMap<>();
        for (JSONObject jsonObject : jsonread) {
            JSONObject jo = new JSONObject();
            jo.put("UserID", jsonObject.get("UserID").toString());
            jo.put("user", jsonObject.get("User").toString());
            if (profilereader.get(jo) == null) {
                profilereader.put(jo, 1);
            } else {
                profilereader.put(jo, profilereader.get(jo) + 1);
            }
        }
        // Trier la carte par valeurs à l'aide de Java 8 Stream
        profilereader = profilereader.entrySet().stream().sorted(( o2, o1) -> o2.getValue().compareTo(o1.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, ( oldValue, newValue) -> oldValue, LinkedHashMap::new));
        int i = 0;
        JSONArray profiles = new JSONArray();
        for (Map.Entry m : profilereader.entrySet()) {
            if (i > (profilereader.size() - 5)) {
                JSONObject ProfileReader = new JSONObject();
                JSONObject l = ((JSONObject) (m.getKey()));
                System.out.println(l.toJSONString());
                ProfileReader.put("UserID", l.get("UserID"));
                ProfileReader.put("UserName", l.get("user"));
                ProfileReader.put("Nombre de lecture ", ((Integer) (m.getValue())));
                profiles.add(ProfileReader);
            }
            ++i;
        }
        return profiles;
    }

    public JSONArray ProfilWrite() {
        Map<JSONObject, Integer> profilereader = new HashMap<>();
        for (JSONObject jsonObject : jsonwrite) {
            JSONObject jo = new JSONObject();
            jo.put("UserID", jsonObject.get("UserID").toString());
            jo.put("user", jsonObject.get("User").toString());
            if (profilereader.get(jo) == null) {
                profilereader.put(jo, 1);
            } else {
                profilereader.put(jo, profilereader.get(jo) + 1);
            }
        }
        // Trier la carte par valeurs à l'aide de Java 8 Stream
        profilereader = profilereader.entrySet().stream().sorted(( o2, o1) -> o2.getValue().compareTo(o1.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, ( oldValue, newValue) -> oldValue, LinkedHashMap::new));
        int i = 0;
        JSONArray profiles = new JSONArray();
        for (Map.Entry m : profilereader.entrySet()) {
            if (i > (profilereader.size() - 5)) {
                JSONObject ProfileReader = new JSONObject();
                JSONObject l = ((JSONObject) (m.getKey()));
                System.out.println(l.toJSONString());
                ProfileReader.put("UserID", l.get("UserID"));
                ProfileReader.put("UserName", l.get("user"));
                ProfileReader.put("Nombre de lecture ", ((Integer) (m.getValue())));
                profiles.add(ProfileReader);
            }
            ++i;
        }
        return profiles;
    }

    public JSONArray ProfilPrice() {
        Map<JSONObject, Double> ProfilePrixtotal = new HashMap<>();
        Map<JSONObject, Double> Profilenblecture = new HashMap<>();
        Map<JSONObject, Double> moyenne = new HashMap<>();
        for (JSONObject jsonObject : jsonprice) {
            JSONObject jo = new JSONObject();
            jo.put("UserID", jsonObject.get("UserID").toString());
            jo.put("user", jsonObject.get("User").toString());
            if (ProfilePrixtotal.get(jo) == null) {
                ProfilePrixtotal.put(jo, Double.valueOf(jsonObject.get("Price").toString()));
            } else {
                ProfilePrixtotal.put(jo, ProfilePrixtotal.get(jo) + Double.valueOf(jsonObject.get("Price").toString()));
            }
        }
        for (JSONObject jsonObject : jsonprice) {
            JSONObject jo = new JSONObject();
            jo.put("UserID", jsonObject.get("UserID").toString());
            jo.put("user", jsonObject.get("User").toString());
            if (Profilenblecture.get(jo) == null) {
                Profilenblecture.put(jo, 1.0);
            } else {
                Profilenblecture.put(jo, Profilenblecture.get(jo) + 1);
            }
        }
        for (Map.Entry m : ProfilePrixtotal.entrySet()) {
            double moyen = Double.valueOf(m.getValue().toString()) / Profilenblecture.get(m.getKey());
            moyenne.put(((JSONObject) (m.getKey())), moyen);
        }
        // Trier la carte par valeurs à l'aide de Java 8 Stream
        moyenne = moyenne.entrySet().stream().sorted(( o2, o1) -> o2.getValue().compareTo(o1.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, ( oldValue, newValue) -> oldValue, LinkedHashMap::new));
        int i = 0;
        JSONArray profiles = new JSONArray();
        for (Map.Entry m : moyenne.entrySet()) {
            if (i > (moyenne.size() - 5)) {
                JSONObject ProfileReader = new JSONObject();
                JSONObject l = ((JSONObject) (m.getKey()));
                System.out.println(l.toJSONString());
                ProfileReader.put("UserID", l.get("UserID"));
                ProfileReader.put("UserName", l.get("user"));
                ProfileReader.put("Prix moyen ", ((Double) (m.getValue())));
                profiles.add(ProfileReader);
            }
            ++i;
        }
        System.out.println("LA " + profiles);
        return profiles;
    }

    public void WriteProfilReader() throws IOException {
        File file = new File("ProfilReader.json");
        // créer le fichier s'il n'existe pas
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(new ObjectMapper().writeValueAsString(ProfilReader()));
        bw.close();
    }

    public void WriteProfilWrite() throws IOException {
        File file = new File("ProfilWrite.json");
        // créer le fichier s'il n'existe pas
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(new ObjectMapper().writeValueAsString(ProfilWrite()));
        bw.close();
    }

    public void WriteProfilPrice() throws IOException {
        File file = new File("ProfilPrice.json");
        // créer le fichier s'il n'existe pas
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(new ObjectMapper().writeValueAsString(ProfilPrice()));
        bw.close();
    }
}
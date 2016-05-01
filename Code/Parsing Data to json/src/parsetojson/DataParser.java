/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsetojson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author palashkochar
 */
public class DataParser {

    private BufferedReader reader = null;
    private String inputLine = null;
    private String filePath = null;
    private int ID;
    FileWriter writer;
    Map<String, Integer> customerIds = new HashMap<>();
    int countCustomers = 1;

    public DataParser(String filePath) throws IOException {
        this.filePath = filePath;
    }

    public void parse() {
        try {
            if (reader != null) {
                reader.close();
            }
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)), 1024 * 100);
            inputLine = reader.readLine();
            while (inputLine != null) {
                if (inputLine.startsWith("Id:")) {
                    ID = extractProductID(inputLine);
                    inputLine = processInputLines(reader, ID);
                } else {
                    inputLine = reader.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int extractProductID(String input) {
        String extractedText = null;
        if (input != null && input.startsWith("Id:")) {
            int pos = input.indexOf(':');
            extractedText = input.substring(pos + 1).trim();
            if (extractedText != null) {
                extractedText = extractedText.trim();
            }
        }
        return Integer.parseInt(extractedText);
    }

    private String processInputLines(BufferedReader reader, int currentId) throws IOException {

        writer = new FileWriter("/Users/palashkochar/amazonUglyMapped.json", true);
        CompleteObject co = new CompleteObject();

        String line = reader.readLine();
        co.setId(currentId);
        while (line != null && !line.startsWith("Id:")) {
            //System.out.println(line);
            if (!line.contains(":")) {
                line = reader.readLine();
                continue;
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index).trim();
            if (line.contains("cutomer")) {
                key = "customer";
            }
            switch (key) {
                case "ASIN":
                    co.setASIN(line.substring(index + 1).trim());
                    break;

                case "title":
                    co.setTitle(line.substring(index + 1).trim());
                    break;

                case "group":
                    co.setGroup(line.substring(index + 1).trim());
                    break;

                case "salesrank":
                    co.setSalesrank(line.substring(index + 1).trim());
                    break;

                case "similar":
                    co.setSimilar(makeList(line.substring(index + 1).trim()));
                    break;

                case "reviews":
                    co.setReviews(makeReviews(line.substring(index + 1).trim()));
                    break;

                case "customer":
                    co.setReviews(makeCustomers(line.substring(index + 1), co.getReviews()));
                    break;
            }
            line = reader.readLine();
        }
        //System.out.println("complete object: "+co);
        Gson gson = new Gson();
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(co);
        
        writer.write(json.concat("\n"));
        writer.close();
        return line;
    }

    private List<String> makeList(String line) {
        List<String> similarTokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(line);
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            if (i == 0) {
                i++;
                tokenizer.nextToken();
                continue;
            }
            similarTokens.add(tokenizer.nextToken());
        }
        return similarTokens;
    }

    private Reviews makeReviews(String line) {
        Reviews r = new Reviews();
        int total, downloaded;
        float avgrating;
        String[] splitString = line.split(".*total:|\\s+downloaded:|\\s+avg rating:");
        total = Integer.parseInt(splitString[1].trim());
        downloaded = Integer.parseInt(splitString[2].trim());
        avgrating = Float.parseFloat(splitString[3].trim());
        r.setTotal(total);
        r.setDownloaded(downloaded);
        r.setAvgRating(avgrating);
        return r;
    }

    private Reviews makeCustomers(String line, Reviews reviews) {
        Reviews r = reviews;
        Customers c = new Customers();
        String customerid;
        int rating;
        int votes;
        int helpful;

        String[] splitString = line.split(".*cutomer:|\\s+rating:|\\s+votes:|\\shelpful:");
        
        int customerIntId;
        
        customerid = splitString[0].trim();
        if(customerIds.containsKey(customerid)){
            customerIntId = customerIds.get(customerid);
        } else{
            countCustomers++;
            customerIds.put(customerid, countCustomers);
            customerIntId = countCustomers;
        }
        rating = Integer.parseInt(splitString[1].trim());
        votes = Integer.parseInt(splitString[2].trim());
        helpful = Integer.parseInt(splitString[3].trim());

        c.setCustomerid(customerIntId);
        c.setRating(rating);
        c.setVotes(votes);
        c.setHelpful(helpful);
        r.getCustomers().add(c);
        return r;
    }
}

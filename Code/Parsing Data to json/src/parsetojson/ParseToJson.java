/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsetojson;

import java.io.IOException;

/**
 *
 * @author palashkochar
 */
public class ParseToJson {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String filePath="/Users/palashkochar/amazon-meta.txt";
        new DataParser(filePath).parse();
    }
    
}

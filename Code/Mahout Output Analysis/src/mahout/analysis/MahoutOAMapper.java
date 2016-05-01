/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahout.analysis;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author palashkochar
 */
public class MahoutOAMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

    private static final IntWritable ONE = new IntWritable(1);
    private Text id = new Text();
    
    @Override
    public void map(LongWritable fileOffset, Text lineContents, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {

        String bookID;
        String line = lineContents.toString();
        int pos = line.indexOf("[");
        String bookIdsRow = line.substring(pos+1, line.length()-1);
        //String[] bookIds = bookIdsRow.split(",");
        StringTokenizer tokenizer = new StringTokenizer(bookIdsRow, ",");
        while (tokenizer.hasMoreElements()) {
            String nextElement = tokenizer.nextToken();
            bookID = (nextElement.split(":"))[0];
            id.set(bookID);
            output.collect(id, ONE);
        }
    }
}

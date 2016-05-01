/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahout.analysis;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author palashkochar
 */
public class MahoutOAReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text id, Iterator<IntWritable> counts, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {

        int totalCount = 0;
        while (counts.hasNext()) {
            IntWritable count = counts.next();
            totalCount += count.get();
        }

        if (totalCount > 100) {
            output.collect(id, new IntWritable(totalCount));
        }
    }
}

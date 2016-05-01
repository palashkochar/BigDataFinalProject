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
public class MahoutOASortReducer extends MapReduceBase implements Reducer<IntWritable, Text, Text, IntWritable> {

    //Log log = LogFactory.getLog(IpReducer.class);
    public void reduce(IntWritable count, Iterator<Text> ids, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {

        Text id = new Text();

        while (ids.hasNext()) {
            id = ids.next();
        }
        output.collect(id, count);
    }
}

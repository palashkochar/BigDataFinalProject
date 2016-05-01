/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahout.analysis;

import java.io.IOException;
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
public class MahoutOASortMapper extends MapReduceBase
        implements Mapper<LongWritable, Text, IntWritable, Text> {

//Log log = LogFactory.getLog(MahoutOASortMapper.class);
    private Text word = new Text();

    public void map(LongWritable fileOffset, Text lineContents, OutputCollector<IntWritable, Text> output, Reporter reporter) throws IOException {

        String text = lineContents.toString();
        String[] lines = text.split("\n");

        int count = 0;
        for (String oneline : lines) {
            String[] tokens = oneline.split("\t");

            count = Integer.parseInt(tokens[1]);
            Text ids = new Text(tokens[0]);
            output.collect(new IntWritable(count), ids);
        }
    }

}

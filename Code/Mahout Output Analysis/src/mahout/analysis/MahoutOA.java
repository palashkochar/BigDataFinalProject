/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahout.analysis;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;

/**
 *
 * @author palashkochar
 */
@SuppressWarnings("deprecation")
public class MahoutOA {

    private static final String OUTPUT_PATH = "outputMid";
    public static void main(String[] args) throws Exception {
        
        Path outPath = new Path(OUTPUT_PATH);
        JobConf conf = new JobConf(MahoutOA.class);
        conf.setJobName("Recommended books count");

        conf.setMapperClass(MahoutOAMapper.class);
        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(IntWritable.class);

        conf.setReducerClass(MahoutOAReducer.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, outPath);

        try {
            FileSystem dfs = FileSystem.get(outPath.toUri(), conf);
            if (dfs.exists(outPath)) {
                dfs.delete(outPath, true);
            }
            JobClient.runJob(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JobConf conf1 = new JobConf(MahoutOA.class);
        Path outPathFinal = new Path(args[1]);

        conf1.setJobName("Recommended books count ordered");

        conf1.setMapperClass(MahoutOASortMapper.class);

        conf1.setMapOutputKeyClass(IntWritable.class);
        conf1.setMapOutputValueClass(Text.class);
        conf1.setOutputKeyComparatorClass(MahoutOAComparator.class);

        conf1.setReducerClass(MahoutOASortReducer.class);

        FileInputFormat.setInputPaths(conf1, new Path(OUTPUT_PATH));
        FileOutputFormat.setOutputPath(conf1, outPathFinal);

        try {
            FileSystem dfs = FileSystem.get(outPathFinal.toUri(), conf);
            if (dfs.exists(outPathFinal)) {
                dfs.delete(outPathFinal, true);
            }
            JobClient.runJob(conf1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

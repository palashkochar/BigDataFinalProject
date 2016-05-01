/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahout.analysis;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparator;
import java.nio.ByteBuffer;

/**
 *
 * @author palashkochar
 */
public class MahoutOAComparator extends WritableComparator {

    public MahoutOAComparator() {
        super(IntWritable.class);
    }

    @Override
    public int compare(byte[] b1, int s1, int l1,
            byte[] b2, int s2, int l2) {

        Integer v1 = ByteBuffer.wrap(b1, s1, l1).getInt();
        Integer v2 = ByteBuffer.wrap(b2, s2, l2).getInt();

        return v1.compareTo(v2) * (-1);
    }

    /*@Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
        int i1 = readInt(b1, s1);
        int i2 = readInt(b2, s2);
         
        int comp = (i1 < i2) ? 1 : (i1 == i2) ? 0 : -1;
        if(0 != comp)
            return comp;
         
        int j1 = readInt(b1, s1+4);
        int j2 = readInt(b2, s2+4);
        comp = (j1 < j2) ? 1 : (j1 == j2) ? 0 : -1;
         
        return comp;
    }*/
}

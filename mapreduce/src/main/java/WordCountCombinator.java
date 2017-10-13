import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.google.common.collect.Lists;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import javax.naming.Context;

public class WordCountCombinator extends Reducer <Text, Text, Text, Text> {
    @Override
    public void reduce (Text key, Iterable <Text> values, Context context) throws IOException, InterruptedException {
        // put your code here!!
        String sum="";
        String key1="";
        key1 = key1+key;
//    String line = values.toString();
//      StringTokenizer itr = new StringTokenizer(line);
//      while(itr.hasMoreTokens())
//      {
//          context.write(new Text(itr.nextToken()), new Text(key));
//      }
        int occ = 0;
//        occ = Lists.newArrayList(values).size();
        ArrayList<String> cache = new ArrayList<>();

        for(Text value: values){
            System.out.println("ADDINGGGG%%%%%%%%%%%%%%%%%%%%%%%%%"+value);
            cache.add(value.toString());
            System.out.println("SHOWINGGGGGGGGGGGGG%%%%%%%%%%%%%%%%%%%%%%%%%"+cache.get(occ));
            occ++;
        }
        Iterator<String> iterator = cache.iterator();
        while (iterator.hasNext()){
            String val= iterator.next();
            System.out.println("value ********************************************"+val);
            System.out.println("key "+key);
//            occ = (int)Math.random()*100;

            System.out.println("IS THIS EVEN RUNNING??????????????????????????????????????????????????????????");

//      StringTokenizer itr = new StringTokenizer(line);

//      while(itr.hasMoreTokens())
//      {
//          context.write(new Text(itr.nextToken()), new Text(key));
//      }

            context.write(new Text(val),new Text(key1+","+occ));

        }


    }

}

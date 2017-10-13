import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import javax.naming.Context;

public class WordCountReducer extends Reducer <Text, Text, Text, Text> {
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
      ArrayList<String> cache = new ArrayList<>();

      for(Text value: values){
          System.out.println("ADDINGGGG%%%%%%%%%%%%%%%%%%%%%%%%%"+value);
          cache.add(value.toString());
          System.out.println("SHOWINGGGGGGGGGGGGG%%%%%%%%%%%%%%%%%%%%%%%%%"+cache.get(occ));
          occ++;
      }

      Collections.sort(cache, new Comparator<String>() {
          @Override
          public int compare(String o1, String o2) {

              return o2.substring(o2.indexOf(",")).compareTo(o1.substring(o1.indexOf(",")));

          }
      });
      Iterator<String> iterator = cache.iterator();

    for(String value: cache){

        System.out.println("value "+value);
        System.out.println("key "+key);

//      StringTokenizer itr = new StringTokenizer(line);

//      while(itr.hasMoreTokens())
//      {
//          context.write(new Text(itr.nextToken()), new Text(key));
//      }
        occ++;
        sum+= value+", ";

    }
      context.write(key,new Text(sum));


  }

}

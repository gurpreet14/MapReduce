import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;

public class WordCountMapperdummy extends Mapper <LongWritable, Text, Text, Text> {

    // create these guys up here for speed
    private final static IntWritable one = new IntWritable (1);
    private Text word = new Text();

    // create a Pattern object to parse each line
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fsFileSplit = (FileSplit) context.getInputSplit();
        String filename = context.getConfiguration().get(fsFileSplit.getPath().getParent().getName());
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+filename);
    }

    public void map (LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // put your code here!
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        String line = value.toString();
        Configuration conf = context.getConfiguration();
        String param = conf.get("test");
        StringTokenizer itr = new StringTokenizer(line);
        String key11 = itr.nextToken();
        String val11 = itr.nextToken();
        FileSplit fileSplit = (FileSplit)context.getInputSplit();
        String filename = fileSplit.getPath().getName();
        System.out.println("======================================================================================="+key11);
        System.out.println("======================================================================================="+val11);
//      System.out.println(filename);

        context.write(new Text(key11),new Text(val11));

//
//        FileSplit fsFileSplit = (FileSplit) context.getInputSplit();
//        String filename = context.getConfiguration().get(fsFileSplit.getPath().getParent().getName());
//        while(itr.hasMoreTokens())
//        {
////        FileSplit fileSplit = (FileSplit)context.getInputSplit();
////        String filename = fileSplit.getPath().getName();
//            System.out.println("======================================================================================="+param);
////      System.out.println(itr.nextToken());
//            System.out.println("=======================================================================================");
//
//            word.set(itr.nextToken());
////      word.set(key.toString());
//            System.out.println(word);
//            context.write(new Text(key),);
//        }

    }



}

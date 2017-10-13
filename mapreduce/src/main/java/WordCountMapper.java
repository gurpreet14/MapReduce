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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;

public class WordCountMapper extends Mapper <LongWritable, Text, Text, Text> {

  // create these guys up here for speed
  private final static IntWritable one = new IntWritable (1);
  private Text word = new Text();

  // create a Pattern object to parse each line
  @Override
  protected void setup(Context context) throws IOException, InterruptedException {
      FileSplit fsFileSplit = (FileSplit) context.getInputSplit();
      String filename = context.getConfiguration().get(fsFileSplit.getPath().getParent().getName());
      System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+filename);
  }

  public void map (LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    // put your code here!
      System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    String line = value.toString();
    Configuration conf = context.getConfiguration();
    String param = conf.get("test");
    StringTokenizer itr = new StringTokenizer(line);
      FileSplit fileSplit = (FileSplit)context.getInputSplit();
      String filename = fileSplit.getPath().getName();
      System.out.println("======================================================================================="+line);
      System.out.println(fileSplit.getPath());
      String path = fileSplit.getPath().toString();
      String temp = path.substring(path.indexOf("input/")+6);
      String foldername = temp.substring(0,temp.indexOf("/"));

//      context.write(new Text(filename),new Text(line));
        int lineCount = 0;

     Document doc = Jsoup.parse(line);
    Elements fc = doc.select("span[class$=fc]");

    for(Element word: fc)
    {
      String output = filename+"->"+word.id();
      context.write(new Text(foldername),new Text(output));
    }
// START
//      while(itr.hasMoreTokens())
//    {
////        FileSplit fileSplit = (FileSplit)context.getInputSplit();
////        String filename = fileSplit.getPath().getName();
//      System.out.println("======================================================================================="+param);
////      System.out.println(itr.nextToken());
//      System.out.println("=======================================================================================");
//
//      word.set(itr.nextToken());
////      word.set(key.toString());
//        System.out.println(word);
//
//    }
    //END


  }



}

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.File;
import java.io.FilenameFilter;


//public class WordCount extends Configured implements Tool {
//
//    private static final String OUTPUT_PATH = "intermediate_output";
//
//    @Override
//    public int run(String[] args) throws Exception {
//  /*
//   * Job 1
//   */
//        Configuration conf = getConf();
//        FileSystem fs = FileSystem.get(conf);
//        Job job = new Job(conf, "Job1");
//        job.setJarByClass(WordCount.class);
//
//        job.setMapperClass(WordCountMapper.class);
//        job.setReducerClass(WordCountReducer.class);
//
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(Text.class);
//
//        job.setInputFormatClass(FileInputFormat.class);
//        job.setOutputFormatClass(FileOutputFormat.class);
//
//        FileInputFormat.addInputPath(job, new Path("input/test1/test1file1.txt"));
//        FileInputFormat.addInputPath(job, new Path("input/test2/test2file1.txt"));
//        FileInputFormat.addInputPath(job, new Path("input/test2/test2file2.txt"));
//
//
//        TextOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));
//
//        job.waitForCompletion(true); /*this goes to next command after this job is completed. your second job is dependent on your first job.*/
//
//
//  /*
//   * Job 2
//   */
//        Configuration conf2 = getConf();
//        Job job2 = new Job(conf2, "Job 2");
//        job2.setJarByClass(WordCount.class);
//
//        job2.setMapperClass(WordCountMapperdummy.class);
//        job2.setReducerClass(WordCountCombinator.class);
//
//        job2.setOutputKeyClass(Text.class);
//        job2.setOutputValueClass(Text.class);
//
//        job2.setInputFormatClass(FileInputFormat.class);
//        job2.setOutputFormatClass(FileOutputFormat.class);
//
//        TextInputFormat.addInputPath(job2, new Path(OUTPUT_PATH));
//        FileOutputFormat.setOutputPath(job2, new Path(args[1]));
//
//         return job2.waitForCompletion(true) ? 0 : 1;
//    }
//
//    /**
//     * Method Name: main Return type: none Purpose:Read the arguments from
//     * command line and run the Job till completion
//     *
//     */
//    public static void main(String[] args) throws Exception {
//        // TODO Auto-generated method stub
//        if (args.length != 2) {
//            System.err.println("Enter valid number of arguments <Inputdirectory>  <Outputlocation>");
//            System.exit(0);
//        }
//        ToolRunner.run(new Configuration(), new WordCount(), args);
//    }
//}
//


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordCount {
public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        Configuration conf = new Configuration();

        Job job = new Job(conf, "First");
    conf.setBoolean("mapreduce.input.fileinputformat.input.dir.recursive", true);

    job.setJarByClass(WordCount.class);
        job.setMapperClass(WordCountMapper.class);
//        job.setCombinerClass(ChainingMapReduceReducer.class);
        job.setReducerClass(WordCountCombinator.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

    List<String> results = new ArrayList<String>();

    System.out.println("READING-----------------------------"+args[0]);


//    File[] files = new File(args[0]+"exampletest/").listFiles();
////If this pathname does not denote a directory, then listFiles() returns null.
//
//    for (File file : files) {
//        if (file.isFile()) {
//            System.out.println("***************"+file.getName());
//            if (file.getName().indexOf(".java.html")!=-1)
//                FileInputFormat.addInputPath(job, new Path(args[0]+"exampletest/"+file.getName()));
//        }
//    }
//
//    File[] filesn = new File(args[0]+"ReadingIntoBeanTest/").listFiles();
////If this pathname does not denote a directory, then listFiles() returns null.
//
//    for (File file : filesn) {
//        if (file.isFile()) {
//            if (file.getName().indexOf(".java.html")!=-1)
//                FileInputFormat.addInputPath(job, new Path(args[0]+"ReadingIntoBeanTest/"+file.getName()));
//        }
//    }

//    FileInputFormat.addInputPath(job, new Path(args[0]+"test1/test1file1.txt"));
//    FileInputFormat.addInputPath(job, new Path(args[0]+"test2/test1file1.txt"));
//    FileInputFormat.addInputPath(job, new Path(args[0]+"test2/test2file2.txt"));
    FileInputFormat.addInputPath(job,new Path(args[0]+"/exampletest"));
    FileInputFormat.addInputPath(job,new Path(args[0]+"/ReadingIntoBeanTest"));
    FileOutputFormat.setOutputPath(job, new Path(args[1]+"/temp"));
        job.waitForCompletion( true );


        System.out.println("First Job Completed.....Starting Second Job");
        System.out.println(job.isSuccessful());


      /*  FileSystem hdfs = FileSystem.get(conf);

        Path fromPath = new Path("/home/Desktop/temp/output/part-r-00000");
        Path toPath = new Path("/home/Desktop/temp/output1");
        hdfs.rename(fromPath, toPath);
        conf.clear();

        */
        if(job.isSuccessful()){
        Configuration conf1 = new Configuration();
        Job job1 = new Job(conf1,"Second");
        job1.setJarByClass(WordCount.class);
        job1.setMapperClass(WordCountMapperdummy.class);
//        job1.setCombinerClass(ChainingMapReduceReducer1.class);
        job1.setReducerClass(WordCountReducer.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job1, new Path(args[1]+"/temp/part-r-00000"));

//        UNCOMMENT THE LINES AS AND WHEN YOU INCREASE THE NODES FOR DEPLOYMENT.
//            FileInputFormat.addInputPath(job1, new Path(args[1]+"/temp/part-r-00001"));
//            FileInputFormat.addInputPath(job1, new Path(args[1]+"/temp/part-r-00002"));
        FileOutputFormat.setOutputPath(job1, new Path(args[1]+"/final"));
        System.exit(job1.waitForCompletion(true) ? 0 : 1);
        }
        System.exit(job.waitForCompletion(true) ? 0 : 1);

        }

        }

Coverage reports are generated using jacoco and gradle. To automate generaion of reports and copying their output 
for use in the mapreduce program a bash script is written.

1. Run the bash file: bash run_tests.sh
This will generate all the coverage reports and copy them into the input folder for mapreduce.
(Tests are located in src/test/java/gurpreetstests of the module gurpreetkaur_chabada_hw1. The program has two jUnit tests)

2. Now we need to generate the jar for mapreduce.
Run ./gradlew mapreduce:fatJar
This will generate the jar in mapreduce/build/libs
The jar name is mapreduce-all.jar

3. To run this jar locally, setup hadoop and execute the following command from the bin directory of your local installation:

hadoop jar mapreduce-all.jar input/ output

(if using mac please run zip -d mapreduce-all.jar META-INF/LICENSE before running the above command.)

Before running the above command Make sure you have input directory copied to your hdfs, you can do that using:

hadoop fs -mkdir -p input (To create input directory if it does not exist)
hdfs dfs -put <insert path to mapreduce input directory here>/. input
Make sure output directory does not exist in hdfs. Delete it..if it does using:
hadoop fs -rm -r output/

copy the contents of the output directory to local file system using:
hadoop fs -copyToLocal output/ .

View the results stored in output/final folder.
Output format is filename->Line number <space> List of tests which cover this line in descending order

If your hadoop cluster contains more than one node, then uncomment lines 171,172 in WordCount.java and add or reduce them based on the 
cluster nodes. The file works for a configuration with 1 node.

Youtube link for EMR deployment (It is unlisted and only accessible via this link):
https://youtu.be/EHoT8o9eHRI

Results of emr deployment are present in mapreduce/output/aws folder.


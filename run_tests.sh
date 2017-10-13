./gradlew  gurpreetkaur_chabada_hw1:clean

gradle gurpreetkaur_chabada_hw1:jacocoTestReport test --tests *ReadingIntoBeanTest

cp -R gurpreetkaur_chabada_hw1/build/reports/jacoco/test/html/com.opencsv/.  mapreduce/input/ReadingIntoBeanTest
cp -R gurpreetkaur_chabada_hw1/build/reports/jacoco/test/html/com.opencsv.bean/.  mapreduce/input/ReadingIntoBeanTest


./gradlew  gurpreetkaur_chabada_hw1:clean

gradle gurpreetkaur_chabada_hw1:jacocoTestReport test --tests *exampletest

cp -R gurpreetkaur_chabada_hw1/build/reports/jacoco/test/html/com.opencsv/.  mapreduce/input/exampletest
cp -R gurpreetkaur_chabada_hw1/build/reports/jacoco/test/html/com.opencsv.bean/.  mapreduce/input/exampletest



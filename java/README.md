### How to run JMH benchmark

    mvn clean install
    java -jar target/benchmarks.jar


### How to check size of the object

    mvn clean install
    java -javaagent:target/size-estimation.jar -cp target/size-estimation.jar zed2020.ObjectSizeEstimation

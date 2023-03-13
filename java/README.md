### How to run JMH benchmark

    mvn clean install
    java -jar target/benchmarks.jar

Scale object shared across different Quantity objects, though Unit is not
10000 Quantity objects allocating around 5001128 bytes (approximately 500 bytes per instance)


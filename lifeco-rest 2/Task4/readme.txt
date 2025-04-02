To run system run the following commands in separate terminals:

 - mvn spring-boot:run -pl broker
 - mvn spring-boot:run -pl auldfellas
 - mvn spring-boot:run -pl dodgygeezers
 - mvn spring-boot:run -pl girlsallowed
 - mvn exec:java -pl  client

The client first runs a post on /applications then a get on /applications then a get on /applications/{id}
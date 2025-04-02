To run system run the following commands in separate terminals (ensure you are running Java 8):

 > docker compose up --build

 > mvn exec:java -pl  client

The client first runs a post on /applications then a get on /applications then a get on /applications/{id}
Can also use postman
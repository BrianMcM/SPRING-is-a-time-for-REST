To run system run the following commands in separate terminals (ensure you are running Java 8):

 > docker compose up --build

 > mvn exec:java -pl  client

To run Kubernetes pods (currently just auldfellas)


 > kubectl apply -f auldfellas-deployment.yaml
 > kubectl apply -f auldfellas-service.yaml
 > kubectl apply -f broker-deployment.yaml
 > kubectl apply -f broker-service.yaml
 > kubectl scale deployment auldfellas --replicas=5
 > kubectl delete deployment auldfellas

The client first runs a post on /applications then a get on /applications then a get on /applications/{id}
Can also use postman

kubectl apply -f auldfellas-deployment.yaml
kubectl apply -f auldfellas-service.yaml
kubectl apply -f broker-deployment.yaml
kubectl apply -f broker-service.yaml

kubectl delete deployment auldfellas
kubectl delete service auldfellas
kubectl delete deployment broker
kubectl delete service broker

docker build -t broker:latest ./broker

curl -X POST http://localhost:30730/applications -H "Content-Type: application/json" -d '{"name":"Jane Doe"}'
kubectl exec -it <pod-name> -- sh
kubectl exec -it broker-79b5dcc64c-hgkm2  -- sh

broker-79b5dcc64c-hgkm2
kubectl logs broker-79b5dcc64c-tvgnl
kubectl logs auldfellas-7b84bc968d-jm855
kubectl exec -it broker-79b5dcc64c-hgkm2  -- sh


curl -X POST http://auldfellas:8080/quotations/ -H "Content-Type: application/json" -d '{"name":"Jane Doe"}'

curl -X POST http://localhost:32083/applications -H "Content-Type: application/json" -d '{"name":"Rem Collier","gender":"M","age":120,"height":3.609,"weight":70,"smoker":true,"medicalIssues":false}'

curl -X POST http://localhost:30730/applications -H "Content-Type: application/json" -d '{"name":"Niki Collier","gender":"F","age":49,"height":1.5494,"weight":80.0,"smoker":false,"medicalIssues":false}'

curl -X POST http://localhost:30730/applications -H "Content-Type: application/json" -d '{"name":"Old Geeza","gender":"M","age":65,"height":1.6,"weight":100.0,"smoker":true,"medicalIssues":true}'

curl -X POST http://localhost:30730/applications -H "Content-Type: application/json" -d '{"name":"Hannah Montana","gender":"F","age":21,"height":1.78,"weight":65.0,"smoker":false,"medicalIssues":false}'

curl -X POST http://localhost:30730/applications -H "Content-Type: application/json" -d '{"name":"Rem Collier","gender":"M","age":49,"height":1.8,"weight":120.0,"smoker":false,"medicalIssues":true}'

curl -X POST http://localhost:30730/applications -H "Content-Type: application/json" -d '{"name":"Jim Quinn","gender":"M","age":55,"height":1.9,"weight":75.0,"smoker":true,"medicalIssues":false}'

curl -X POST http://localhost:31039/applications -H "Content-Type: application/json" -d '{"name":"Donald Duck","gender":"M","age":35,"height":0.45,"weight":1.6,"smoker":false,"medicalIssues":false}'


kubectl autoscale deployment auldfellas --cpu-percent=50 --min=1 --max=5

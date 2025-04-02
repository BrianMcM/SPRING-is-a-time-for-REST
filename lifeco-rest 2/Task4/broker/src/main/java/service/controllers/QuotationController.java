package service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.core.Application;
import service.core.ClientInfo;
import service.core.Quotation;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;


@RestController
public class QuotationController {
    private final Map<String, Application> applications = new TreeMap<>();

    @GetMapping(value="/applications", produces="application/json")
    public ResponseEntity<ArrayList<String>> getApplications() {
        ArrayList<String> list = new ArrayList<>();
        for (Application application : applications.values()) {
            list.add("http://" + getHost() + "/applications/"+application.id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value="/applications/{id}", produces={"application/json"})
    public ResponseEntity<Application> getApplication(@PathVariable String id) {
        Application application = applications.get(id);
        if (application == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(application);
    }


    public ResponseEntity<Quotation> makePost(String url, ClientInfo info){
        RestTemplate template = new RestTemplate();
        ResponseEntity<Quotation> response = template.postForEntity(url, info, Quotation.class);
        if (response.getStatusCode().equals(HttpStatus.CREATED)) {
            System.out.println("Location of resource: " + response.getHeaders().getLocation().toString());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
    }

    @PostMapping(value="/applications", consumes="application/json")
    public ResponseEntity<Application> createApplication(@RequestBody ClientInfo info) {

        Application application = new Application(info);

        List<String> servicesList = Arrays.asList(
                "http://localhost:8080/quotations/",
                "http://localhost:8081/quotations/",
                "http://localhost:8082/quotations/"
        );

        for (String postLocation : servicesList){
            Quotation returnQuotation = makePost(postLocation, info).getBody();
            application.quotations.add(returnQuotation);
        }

        applications.put(String.valueOf(application.id), application);
        String url = "http://"+getHost()+"/applications/" + application.id;
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", url)
                .header("Content-Location", url)
                .body(application);
    }

    private String getHost() {
        int port = 8083;
        try {
            return InetAddress.getLocalHost().getHostAddress() + ":" + port;
        } catch (UnknownHostException e) {
            return "localhost:" + port;
        }
    }
}
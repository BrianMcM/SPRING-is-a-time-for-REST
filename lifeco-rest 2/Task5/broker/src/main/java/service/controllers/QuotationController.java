package service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.core.Application;
import service.core.ClientInfo;
import service.core.Quotation;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.*;


@RestController
public class QuotationController {
//    private final Map<String, Application> applications = new TreeMap<>();
//
//    @GetMapping(value="/applications", produces="application/json")
//    public ResponseEntity<ArrayList<String>> getApplications() {
//        ArrayList<String> list = new ArrayList<>();
//        for (Application application : applications.values()) {
//            list.add("http://" + getHost() + "/applications/"+application.id);
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(list);
//    }
//
//    @GetMapping(value="/applications/{id}", produces={"application/json"})
//    public ResponseEntity<Application> getApplication(@PathVariable String id) {
//        Application application = applications.get(id);
//        if (application == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(application);
//    }


    public ResponseEntity<Quotation> makePost(String url, ClientInfo info){
        System.out.println("A");
        RestTemplate template = new RestTemplate();
        System.out.println("B");
        ResponseEntity<Quotation> response = template.postForEntity(url, info, Quotation.class);
        System.out.println("C");
//        if (response.getStatusCode().equals(HttpStatus.CREATED)) {
//            System.out.println("D");
//            System.out.println("Location of resource: " + response.getHeaders().getLocation().toString());
//        }
        if (response.getStatusCode().equals(HttpStatus.CREATED)) {
            System.out.println("D");
            URI location = response.getHeaders().getLocation();
            if (location != null) {
                System.out.println("Location of resource: " + location);
            } else {
                System.out.println("No Location header in response");
            }
        }

        System.out.println("E");
        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
    }

    @PostMapping(value="/applications", consumes="application/json")
    public ResponseEntity<Application> createApplication(@RequestBody ClientInfo info) {

        Application application = new Application(info);

//        List<String> servicesList = Arrays.asList(
//                "http://auldfellas:8080/quotations/"
////                "http://girlsallowed:8081/quotations/",
////                "http://dodgygeezers:8082/quotations/"
//        );
        String auldfellasUrl = "http://auldfellas:8080/quotations/";
        String girlsallowedUrl = "http://girlsallowed:8081/quotations/";
        String dodgygeezersUrl = "http://dodgygeezers:8082/quotations/";

        try {
            ResponseEntity<Quotation> response = makePost(auldfellasUrl, info);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Quotation body: " + response.getBody().toString());

            Quotation returnQuotation = response.getBody();
            if (returnQuotation == null) {
                System.out.println("Quotation was null!");
            } else {
                application.quotations.add(returnQuotation);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while posting to auldfellas:");
            e.printStackTrace();
        }
        try {
            ResponseEntity<Quotation> response = makePost(girlsallowedUrl, info);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Quotation body: " + response.getBody().toString());

            Quotation returnQuotation = response.getBody();
            if (returnQuotation == null) {
                System.out.println("Quotation was null!");
            } else {
                application.quotations.add(returnQuotation);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while posting to auldfellas:");
            e.printStackTrace();
        }
        try {
            ResponseEntity<Quotation> response = makePost(dodgygeezersUrl, info);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Quotation body: " + response.getBody().toString());

            Quotation returnQuotation = response.getBody();
            if (returnQuotation == null) {
                System.out.println("Quotation was null!");
            } else {
                application.quotations.add(returnQuotation);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while posting to auldfellas:");
            e.printStackTrace();
        }


//        for (String postLocation : servicesList){
//            Quotation returnQuotation = makePost(postLocation, info).getBody();
//            application.quotations.add(returnQuotation);
//        }

//        applications.put(String.valueOf(application.id), application);
//        String url = "http://"+getHost()+"/applications/" + application.id;
        return ResponseEntity
                .status(HttpStatus.CREATED)
//                .header("Location", url)
//                .header("Content-Location", url)
                .body(application);
    }

//    private String getHost() {
//        int port = 8083;
//        return "localhost:" + port;
////        try {
////            return "localhost:" + port;
//////            return InetAddress.getLocalHost().getHostAddress() + ":" + port;
////        } catch (UnknownHostException e) {
////            return "localhost:" + port;
////        }
//    }
}
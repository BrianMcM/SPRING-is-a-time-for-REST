package service.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AFQService;
import service.core.ClientInfo;
import service.core.Quotation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class QuotationController {
    @Value("${HOSTNAME:unknown}")
    private String podName;
    private AFQService service = new AFQService();
//    @PostMapping(value="/quotations", consumes="application/json", produces="application/json")
//    public ResponseEntity<Map<String, Object>> createQuotation(@RequestBody ClientInfo info) {
//        // Dummy quotation
//        Quotation quotation = new Quotation(UUID.randomUUID().toString(), "AuldFellas", 1234.56);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("quotation", quotation);
//        response.put("servedBy", podName);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
    @PostMapping(value="/quotations", consumes="application/json")
    public ResponseEntity<Quotation> createQuotation(@RequestBody ClientInfo info) {
        Quotation quotation = service.generateQuotation(info);
//        quotations.put(quotation.reference, quotation);
//        String url = "http://"+getHost()+"/quotations/" + quotation.reference;
        return ResponseEntity
                .status(HttpStatus.CREATED)
//                .header("Location", url)
//                .header("Content-Location", url)
                .body(quotation);
    }
}

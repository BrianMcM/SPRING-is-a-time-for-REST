//import com.sun.security.ntlm.Client;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.client.methods.CloseableHttpResponse;
import service.core.ClientInfo;
//import org.apache.*;
//org.apache.http
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import service.core.Quotation;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "http://localhost:8083/applications";
        List<ClientInfo> clientInfoList = new ArrayList<>();
//        ClientInfo info = new ClientInfo("Rem Collier", 'M', 120, 3.609, 70, true, false);
        clientInfoList.add(new ClientInfo("Rem Collier", 'M',120,3.609,70,true,false));
        clientInfoList.add(new ClientInfo("Niki Collier", 'F', 49, 1.5494, 80.0, false, false));
        clientInfoList.add(new ClientInfo("Old Geeza", 'M', 65, 1.6, 100.0, true, true));
        clientInfoList.add(new ClientInfo("Hannah Montana", 'F', 21, 1.78, 65.0, false, false));
        clientInfoList.add(new ClientInfo("Rem Collier", 'M', 49, 1.8, 120.0, false, true));
        clientInfoList.add(new ClientInfo("Jim Quinn", 'M', 55, 1.9, 75.0, true, false));
        clientInfoList.add(new ClientInfo("Donald Duck", 'M', 35, 0.45, 1.6, false, false));
        ObjectMapper objectMapper = new ObjectMapper();

        for (ClientInfo client:clientInfoList){
//            Print client info
            displayProfile(client);
//            create post
            String jsonBody = objectMapper.writeValueAsString(client);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(jsonBody));
            httpPost.setHeader("Content-Type", "application/json");
            CloseableHttpResponse response1 = httpClient.execute(httpPost);

            HttpEntity entity1 = response1.getEntity();
            String postResponse = EntityUtils.toString(entity1);
            JsonNode jsonNode = objectMapper.readTree(postResponse);
            int applicationID = jsonNode.get("id").asInt();
//            Start get
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String[] urlArray = objectMapper.readValue(EntityUtils.toString(entity), String[].class);
            //end get

            // start get
            String applicationReturn = "http://localhost:8083/applications/"+applicationID;
            HttpGet httpGet2 = new HttpGet(applicationReturn);
            CloseableHttpResponse response3 = httpClient.execute(httpGet2);
            HttpEntity entity3 = response3.getEntity();
            String finalResponseString = EntityUtils.toString(entity3);

            JsonNode responseJson = objectMapper.readTree(finalResponseString);

            // Deserialize the 'quotations' array into a List of Quotation objects
            List<Quotation> quotations = new ArrayList<>();
            if (responseJson.has("quotations")) {
                for (JsonNode quotationNode : responseJson.get("quotations")) {
                    Quotation quotation = objectMapper.treeToValue(quotationNode, Quotation.class);
                    quotations.add(quotation);
                }
            }


            for (Quotation quote: quotations){
                displayQuotation(quote);
            }
        }
    }

//    implemented a method to search return strings for id.
//    But this is just not needed as we already have the ID from the return of the post
    private static String searchGet(String[] urlArray, int applicationID) {
        for (String urlI : urlArray){
            String[] parts = urlI.split("/");
            int number = Integer.parseInt(parts[parts.length - 1]);
            if (number == applicationID){
                return urlI;
            }
        }
        return null;
    }

    public static void displayProfile(ClientInfo info) {
        System.out.println("|=================================================================================================================|");
        System.out.println("|                                     |                                     |                                     |");
        System.out.println("| Name: " + String.format("%1$-29s", info.name) + " | Gender: " + String.format("%1$-27s", info.gender == 'M' ? "Male" : "Female") + " | Age: " + String.format("%1$-30s", info.age) + " |");
        System.out.println("| Weight/Height: " + String.format("%1$-20s", info.weight + "kg/" + info.height + "m") + " | Smoker: " + String.format("%1$-27s", info.smoker ? "YES" : "NO") + " | Medical Problems: " + String.format("%1$-17s", info.medicalIssues ? "YES" : "NO") + " |");
        System.out.println("|                                     |                                     |                                     |");
        System.out.println("|=================================================================================================================|");
    }

    public static void displayQuotation(Quotation quotation) {
        System.out.println("| Company: " + String.format("%1$-26s", quotation.company) + " | Reference: " + String.format("%1$-24s", quotation.reference) + " | Price: " + String.format("%1$-28s", NumberFormat.getCurrencyInstance().format(quotation.price)) + " |");
        System.out.println("|=================================================================================================================|");
    }
}

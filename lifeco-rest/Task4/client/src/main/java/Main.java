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

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "http://localhost:8083/applications";
        ClientInfo info = new ClientInfo("Rem Collier", 'M',120,3.609,70,true,false);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(info);
//post
        HttpPost httpPost = new HttpPost(url);
        System.out.println("JSON Body: " + jsonBody);
        httpPost.setEntity(new StringEntity(jsonBody));
        httpPost.setHeader("Content-Type", "application/json");
        CloseableHttpResponse response1 = httpClient.execute(httpPost);

        HttpEntity entity1 = response1.getEntity();
        String postResponse = EntityUtils.toString(entity1);
        System.out.println("POST Response: " + postResponse);
        JsonNode jsonNode = objectMapper.readTree(postResponse);
        int applicationID = jsonNode.get("id").asInt();
        System.out.print(applicationID);
        //end post

        //startget

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        String[] urlArray = objectMapper.readValue(EntityUtils.toString(entity), String[].class);

        System.out.print(Arrays.toString(urlArray));
        //end get

        // start get
        String applicationReturn = searchGet(urlArray, applicationID);
        HttpGet httpGet2 = new HttpGet(applicationReturn);
        CloseableHttpResponse response3 = httpClient.execute(httpGet2);
        HttpEntity entity3 = response3.getEntity();
        System.out.println("GET individual response: " + EntityUtils.toString(entity3));
        //end get


    }

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
}

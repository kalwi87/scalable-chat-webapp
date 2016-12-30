package biel.chat.client.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Base64;


public class AbstractRemoteService {
    @Autowired
    protected RestTemplate  restTemplate;
   
    @Value("${auth.username}")
    private String username;
    @Value("${auth.password}")
    private String password;
    @Value("${remote.baseUrl}")
    private String remoteBaseUrl;

    protected URI prepareUrl(String resourceUrl){
        return URI.create(remoteBaseUrl + resourceUrl);
    }

    protected HttpHeaders getDefaultHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", getAuthenticationHeader());
        return headers;
    }

    private String getAuthenticationHeader(){
        String regularText = username + ":" + password;
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encoded = encoder.encode(regularText.getBytes(Charset.forName("UTF-8")));
        return "Basic " + new String(encoded, Charset.forName("UTF-8"));
    }
}

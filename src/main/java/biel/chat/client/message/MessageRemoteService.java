package biel.chat.client.message;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import biel.chat.client.user.AbstractRemoteService;
@Service
public class MessageRemoteService extends AbstractRemoteService {
	
	 public Message postMessageToRemote(Message message){
	        HttpHeaders headers = getDefaultHeaders();
	        
			RequestEntity request = new RequestEntity(message,headers,HttpMethod.POST,prepareUrl("/api/message"));
			
	        return restTemplate.exchange(request, new ParameterizedTypeReference<Message>() {}).getBody();
	        
	        
	    }
	 public List<Message> getAvailableMessages(){
	        HttpHeaders headers = getDefaultHeaders();
	        RequestEntity request = new RequestEntity(headers, HttpMethod.GET, prepareUrl("/api/message"));
	        return restTemplate.exchange(request, new ParameterizedTypeReference<List<Message>>() {}).getBody();
	    }
	
	

}

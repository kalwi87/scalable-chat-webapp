package biel.chat.client.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import biel.chat.client.user.User;

import java.util.Date;
import java.util.List;


@RequestMapping("/message")
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;
    
    @Autowired
    MessageRemoteService mssageRemoteService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Message> getAllMessages(){
        return messageService.findAll();
    }


    @PostMapping
    public Message createIncoming(@RequestBody Message message){
    	
    	message.setCreated(new Date());
    	mssageRemoteService.postMessageToRemote(message);
    	return messageService.create(message);
    	
    }
    @GetMapping("/remote")
    public List<Message> getMessages(){
    	
    	List<Message> list = mssageRemoteService.getAvailableMessages();
    	
    	for(Message item : list){
    		item.setCreated(new Date());
    		messageService.create(item);
    	}
    	
		return list;
    	
    }
    @GetMapping("/delete")
    public int deleteAllFromMessages(){
		return messageService.deleteAll();
    	
    }
}

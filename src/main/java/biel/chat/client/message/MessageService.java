package biel.chat.client.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public Message create(Message message){
        messageRepository.create(message);
        return message;
    }

    public List<Message> findAll(){
        return messageRepository.findAll();
    }
    
    public int deleteAll(){
    	return messageRepository.deleteAllMessages();
    }

}

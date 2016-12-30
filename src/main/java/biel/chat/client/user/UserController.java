package biel.chat.client.user;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
public class UserController {
	
	@Autowired
	UserRemoteService userremoteservice;
	
	@Autowired
	UserService userservice;
	
	@GetMapping
	public List<String> getAllUsers(){
	
		return userremoteservice.getAvailableContacts();
		
	}

}

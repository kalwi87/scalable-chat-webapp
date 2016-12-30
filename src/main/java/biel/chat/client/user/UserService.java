package biel.chat.client.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public void createUser(User user){
		userRepo.create(user);
	}
	
	public List<User> getAllUsers(){
		return  userRepo.findAll();
		
		
	}

}

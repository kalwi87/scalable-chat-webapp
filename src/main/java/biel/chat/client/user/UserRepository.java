package biel.chat.client.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biel.chat.client.message.Message;

@Repository
public class UserRepository {
	 
		@PersistenceContext
	    EntityManager entityManager;
		
		public void create(User entity){
			entityManager.persist(entity);
	    }

	    public List<User> findAll() {
	        return entityManager.createNativeQuery("SELECT * FROM USERS",User.class).getResultList();
	    }

}

package biel.chat.client.message;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class MessageRepository {

    @PersistenceContext
    EntityManager em;

    public void create(Message entity){
        em.persist(entity);
    }

    public List<Message> findAll() {
        return em.createNativeQuery("SELECT * FROM MESSAGE",Message.class).getResultList();
    }
    
    public int deleteAllMessages(){
    	return em.createNativeQuery("DELETE FROM MESSAGE").executeUpdate();
    }

	public int delete(String userId) {
		List<Message> allMessages = findAll();
		for(Message item : allMessages){
			if(item.getMessageId().equals(userId)){
				em.remove(item);
				return 1;
			}
		}
		return 0;
		
	}


}

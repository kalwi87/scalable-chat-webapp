package biel.chat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import biel.chat.client.message.Message;
import biel.chat.client.message.MessageService;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MessageServiceTest {

    @Autowired
    MessageService messageService;
    @Autowired
    EntityManager entityManager;

    @Test
    public void should_create_messages(){
        Message message = new Message();
        message.setBody("content");
        message.setCreated(new Date());
        message.setSenderId("senderId");
        message.setReceiverId("receiverId");

        messageService.create(message);

        Message persisted = entityManager.find(Message.class, message.getId());
        assertThat(persisted.getCreated()).isEqualTo(message.getCreated());
        assertThat(persisted.getBody()).isEqualTo(message.getBody());
        assertThat(persisted.getSenderId()).isEqualToIgnoringCase(message.getSenderId());
        assertThat(persisted.getReceiverId()).isEqualTo(message.getReceiverId());
    }

    @Test
    public void should_find_all_messages(){
        Message in = new Message();
        in.setBody("content");
        in.setCreated(new Date());
        in.setSenderId("senderId");

        Message out = new Message();
        out.setBody("content");
        out.setCreated(new Date());
        out.setReceiverId("receiverId");

        entityManager.persist(in);
        entityManager.persist(out);

        List<Message> all = messageService.findAll();

        assertThat(all).contains(in, out);
    }
}
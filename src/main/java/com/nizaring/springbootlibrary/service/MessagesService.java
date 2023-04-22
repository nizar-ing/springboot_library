package com.nizaring.springbootlibrary.service;

import com.nizaring.springbootlibrary.dao.MessageRepository;
import com.nizaring.springbootlibrary.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessagesService {
    private MessageRepository messageRepository;

    @Autowired
    public MessagesService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void postMessage(Message requestMessage, String userEmail){
        var message = new Message(requestMessage.getTitle(), requestMessage.getQuestion());
        message.setUserEmail(userEmail);
        messageRepository.save(message);
    }
}

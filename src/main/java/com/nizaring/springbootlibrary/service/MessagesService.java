package com.nizaring.springbootlibrary.service;

import com.nizaring.springbootlibrary.dao.MessageRepository;
import com.nizaring.springbootlibrary.entity.Message;
import com.nizaring.springbootlibrary.requestmodels.AdminQuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public void putMessage(AdminQuestionRequest adminQuestionRequest, String adminEmail) throws Exception {
        Optional<Message> message = messageRepository.findById(adminQuestionRequest.getId());
        if(!message.isPresent()) throw new Exception("message not found");
        message.get().setAdminEmail(adminEmail);
        message.get().setResponse(adminQuestionRequest.getResponse());
        message.get().setClosed(true);
        messageRepository.save(message.get());
    }
}

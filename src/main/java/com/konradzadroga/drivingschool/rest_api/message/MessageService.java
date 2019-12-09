package com.konradzadroga.drivingschool.rest_api.message;
import com.konradzadroga.drivingschool.rest_api.user.UserBasicInfoDTO;
import com.konradzadroga.drivingschool.rest_api.user.User;
import com.konradzadroga.drivingschool.rest_api.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    private UserService userService;
    private MessageRepository messageRepository;

    public MessageService(UserService userService, MessageRepository messageRepository) {
        this.userService = userService;
        this.messageRepository = messageRepository;
    }

    public User getReceiver(String username) {
        User user = userService.findUserByUsername(username);
        return user;
    }

    public User getCurrentUser() {
        User user = userService.getCurrentUser();
        return user;
    }

    public Message buildMessage(SendMessageDTO sentMessage) {
        Message message = new Message();
        message.setContent(sentMessage.getContent());
        message.setReceiver(getReceiver(sentMessage.getReceiverUsername()));
        message.setSender(getCurrentUser());
        message.setSentDate(new Date());
        return message;
    }

    public void sendMessage(SendMessageDTO sentMessage) {
        messageRepository.save(buildMessage(sentMessage));
    }

    public void addMessagesToList(List<MessageDTO> messages, String senderUsername, String receiverUsername) {
        messageRepository.getAllBySenderUsernameAndReceiverUsername(senderUsername, receiverUsername).forEach(
                message -> {
                    messages.add(new MessageDTO(
                            message.getId(),
                            message.getContent(),
                            message.getSentDate(),
                            new UserBasicInfoDTO(message.getSender()),
                            new UserBasicInfoDTO(message.getReceiver())
                    ));
                }
        );
    }

    public List<MessageDTO> getMessagesWithParticularUser(String username) {
        List<MessageDTO> messages = new ArrayList<>();
        User currentUser = getCurrentUser();
        addMessagesToList(messages, currentUser.getUsername(), username);
        addMessagesToList(messages, username, currentUser.getUsername());
        messages.sort(Comparator.comparing(MessageDTO::getSentDate));

        return messages;
    }


}

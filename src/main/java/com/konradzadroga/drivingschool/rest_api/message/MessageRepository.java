package com.konradzadroga.drivingschool.rest_api.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getAllBySenderUsernameAndReceiverUsername(String senderUsername, String receiverUsername);
}

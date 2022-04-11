package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Notifications;
import com.itsol.recruit.repository.NotificationsRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsServiceImpl implements NotificationsService {

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Notifications add(Notifications notifications){
        return notificationsRepository.save(notifications);
    }

    @Override
    public List<Notifications> findAll(){
        return notificationsRepository.findAll();
    }

    @Override
    public List<Notifications> findByUser(Long receiverId){

        return notificationsRepository.findByReceiver(userRepository.findOneById(receiverId));
    }
}


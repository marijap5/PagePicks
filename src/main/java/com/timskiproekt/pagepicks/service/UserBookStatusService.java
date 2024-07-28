package com.timskiproekt.pagepicks.service;

import com.timskiproekt.pagepicks.model.UserBookStatus;
import com.timskiproekt.pagepicks.repository.UserBookStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookStatusService {

    private UserBookStatusRepository userBookStatusRepository;

    public List<UserBookStatus> getUserBookStatusesByUserId(Long userId) {
        return userBookStatusRepository.findByUserId(userId);
    }

    public UserBookStatus saveUserBookStatus(UserBookStatus userBookStatus) {
        return userBookStatusRepository.save(userBookStatus);
    }

    public void deleteUserBookStatus(Long id) {
        userBookStatusRepository.deleteById(id);
    }

}

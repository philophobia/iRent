package se.irent.service;

import se.irent.entity.User;

import java.util.List;

interface UserService {
    List<User> findByIdLike(String user_id);
    List<User> findAll();
    void deleteById(String user_id);
}

package se.irent.service;

import se.irent.entity.User;

import java.util.List;

interface UserService {
    List<User> findByIdLike(int user_id);
    List<User> findAll();
    User findById(int user_id);
    void deleteById(int user_id);
    //User update(User user);
}

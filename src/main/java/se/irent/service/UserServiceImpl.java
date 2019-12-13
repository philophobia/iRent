package se.irent.service;

import se.irent.dao.UserRepository;

import javax.annotation.Resource;

public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public void deleteById(String user_id) {
        userRepository.deleteById(user_id);
    }
}

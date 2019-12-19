package se.irent.service;

import org.springframework.stereotype.Service;
import se.irent.dao.UserRepository;
import se.irent.entity.User;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public List<User> findByIdLike(String user_id) {
        return userRepository.findByIdLike("%" + user_id + "%");
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String user_id) {
        userRepository.deleteById(user_id);
    }
}

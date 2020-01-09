package se.irent.service;

import org.springframework.stereotype.Service;
import se.irent.dao.AdminRepository;
import se.irent.entity.Admin;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminRepository adminRepository;

    @Override
    public Admin findById(String admin_id) {
        Optional<Admin> admin = adminRepository.findById(admin_id);
        return admin.orElse(null);
    }
}

package se.irent.service;

import org.springframework.stereotype.Service;
import se.irent.dao.AdministratorRepository;
import se.irent.entity.Administrator;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Resource
    private AdministratorRepository adminRepository;

    @Override
    public Administrator findById(String admin_id) {
        Optional<Administrator> admin = adminRepository.findById(admin_id);
        return admin.orElse(null);
    }
}

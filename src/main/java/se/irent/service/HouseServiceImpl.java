package se.irent.service;

import org.springframework.stereotype.Service;
import se.irent.dao.HouseRepository;
import se.irent.entity.House;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Resource
    private HouseRepository houseRepository;

    @Override
    public List<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public List<House> findByIdLike(String hid) {
        return houseRepository.findByIdLike("%" + hid + "%");
    }

    @Override
    public void deleteById(String hid) {
        houseRepository.deleteById(hid);
    }
}

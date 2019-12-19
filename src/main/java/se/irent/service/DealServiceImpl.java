package se.irent.service;

import org.springframework.stereotype.Service;
import se.irent.dao.DealRepository;
import se.irent.entity.Deal;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class DealServiceImpl implements DealService {
    @Resource
    private DealRepository dealRepository;

    @Override
    public List<Deal> findByIdLike(String param_id) {
        return dealRepository.findByIdLike("%" + param_id + "%");
    }

    @Override
    public List<Deal> findAll() {
        return dealRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        dealRepository.deleteById(id);
    }
}

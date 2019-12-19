package se.irent.service;

import se.irent.entity.Deal;

import java.util.List;

interface DealService {
    List<Deal> findByIdLike(String param_id);
    List<Deal> findAll();
    void deleteById(String id);
}

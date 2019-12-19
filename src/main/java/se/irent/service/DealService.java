package se.irent.service;

import se.irent.entity.Deal;

import java.util.List;

interface DealService {
    Deal findById(String id);
    List<Deal> findAll();
    void deleteById(String id);
}

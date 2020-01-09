package se.irent.service;

import se.irent.entity.House;

import java.util.List;

interface HouseService {
    List<House> findAll();
    List<House> findByIdLike(int hid);
    void deleteById(int hid);
}

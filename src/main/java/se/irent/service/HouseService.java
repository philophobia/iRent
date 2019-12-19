package se.irent.service;

import se.irent.entity.House;

import java.util.List;

interface HouseService {
    List<House> findAll();
    List<House> findByIdLike(String hid);
    void deleteById(String hid);
}

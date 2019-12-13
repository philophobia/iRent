package se.irent.service;

import se.irent.entity.Administrator;

interface AdministratorService {
    Administrator findById(String admin_id);
}

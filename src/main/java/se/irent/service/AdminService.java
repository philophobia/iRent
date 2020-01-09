package se.irent.service;

import se.irent.entity.Admin;

interface AdminService {
    Admin findById(String admin_id);
}

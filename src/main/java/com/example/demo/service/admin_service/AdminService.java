package com.example.demo.service.admin_service;

import com.example.demo.entity.Admin;

public interface AdminService {
    void createAdmin(Admin admin);
    Admin getAdminById(long idAdmin);
}

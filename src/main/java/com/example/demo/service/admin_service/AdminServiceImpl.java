package com.example.demo.service.admin_service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.admin_repository.AdminRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service(value = "adminService")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminServiceImpl implements AdminService {
    final AdminRepository repository;

    public AdminServiceImpl(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createAdmin(Admin admin) {
        repository.save(admin);
    }

    @Override
    public Admin getAdminById(long idAdmin) {
        return repository.findById(idAdmin).orElse(null);
    }
}

package com.ywmobile.app.internship.service.impl;

import com.ywmobile.app.internship.DAO.DepartmentDAO;
import com.ywmobile.app.internship.DAO.StaffDAO;
import com.ywmobile.app.internship.DTO.DepartmentDTO;
import com.ywmobile.app.internship.model.Department;
import com.ywmobile.app.internship.service.IManageDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageDepartmentServiceImpl implements IManageDepartmentService {
    @Autowired
    DepartmentDAO departmentDAO;

    @Autowired
    StaffDAO staffDAO;


    @Override
    public int getTotalItems (String keyword) {
        int totalItems = departmentDAO.getTotalItems(keyword);
        return totalItems;
    }

    @Override
    public Department findDepartmentById(Integer id) {
        Department department = departmentDAO.findDepartmentById(id);
        return department;
    }

    @Override
    public void deleteDepartment(Integer id) {
        staffDAO.setDefaultDepartmentId(id);
        departmentDAO.deleteDepartment(id);
    }

    @Override
    public void save(Department department) {
        departmentDAO.save(department);
    }

    @Override
    public void update(Department department) {
        departmentDAO.update(department);
    }

    @Override
    public boolean getExistByName(String name) {
        return departmentDAO.getExistByName(name);
    }

}

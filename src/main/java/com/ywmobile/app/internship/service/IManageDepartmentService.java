package com.ywmobile.app.internship.service;

import com.ywmobile.app.internship.DTO.DepartmentDTO;
import com.ywmobile.app.internship.model.Department;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IManageDepartmentService {


   int getTotalItems(String keyword);

 Department findDepartmentById(Integer id);

    void deleteDepartment(Integer id);

    void save(Department department);

    void update(Department department);

    public  boolean getExistByName(String name);
}

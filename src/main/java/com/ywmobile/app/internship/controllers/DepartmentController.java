package com.ywmobile.app.internship.controllers;

import com.ywmobile.app.internship.DAO.DepartmentDAO;
import com.ywmobile.app.internship.DTO.PageResponse;
import com.ywmobile.app.internship.DTO.Paging;
import com.ywmobile.app.internship.DTO.PagingResponse;
import com.ywmobile.app.internship.enums.HttpCode;
import com.ywmobile.app.internship.model.Department;
import com.ywmobile.app.internship.service.IManageDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class DepartmentController {
    @Autowired
    IManageDepartmentService manageDepartmentService;

    @Autowired
    DepartmentDAO departmentDAO;

    @GetMapping("/department/list")
    public PageResponse<PagingResponse<List<Department>>> listDepartments(@Valid @RequestParam(required = false) @Min(value = 1, message = "pageNo value is not valid") int pageNo,
                                                                          @RequestParam(required = false) @Min(value = 1, message = "pageSize value is not valid") int pageSize,
                                                                          @RequestParam(required = false) String keyword) {
        try {
            int totalItems = manageDepartmentService.getTotalItems(keyword);
            int totalPages = (int) Math.ceil((double) totalItems / pageSize);
            Paging paging = new Paging(pageSize * (pageNo - 1), pageSize, keyword);
            List<Department> departmentsPaging = departmentDAO.getDepartmentPaging(paging);
            PagingResponse<List<Department>> listPagingResponse = new PagingResponse<>(departmentsPaging, totalItems, totalPages, pageNo);
            if (departmentsPaging.isEmpty()) {
                return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg(), listPagingResponse);
            }
            return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg(), listPagingResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }


    @GetMapping("/department/detail")
    public PageResponse<Department> findDepartment(@RequestParam(required = false) int id) {
        Department department = manageDepartmentService.findDepartmentById(id);
        if (department == null) {
            return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg());
        } else {
            return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg(), department);
        }
    }

    @PostMapping("/department/add")
    public PageResponse<Department> createNewDepartment(@Valid @RequestBody Department department) {
        try {
            if (manageDepartmentService.getExistByName(department.getName())) {
                return new PageResponse<>(HttpCode.EXIST_BY_DEPARTMENTNAME.getCode(), HttpCode.EXIST_BY_DEPARTMENTNAME.getMsg());
            } else {
                manageDepartmentService.save(department);
                return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg());
            }
        } catch (Exception e) {
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }



    @DeleteMapping("/department/delete")
    public PageResponse<Department> deleteDepartment(@Valid @RequestParam int id) {
        Department department = manageDepartmentService.findDepartmentById(id);
        if (department == null) {
            return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg());
        }
        try {
            if (department.isPrimaryKey()) {
                return new PageResponse<>(HttpCode.DELETE_FAIL.getCode(), HttpCode.DELETE_FAIL.getMsg());
            }
            manageDepartmentService.deleteDepartment(id);
            return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg());
        } catch (Exception e) {
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }

    @PutMapping("/department/update")
    public PageResponse<Department> updateDepartment(@Valid @RequestBody Department department) {
        try {
            Department departmentData = manageDepartmentService.findDepartmentById(department.getId());
            if (departmentData == null) {
                return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg());
            }
            if (manageDepartmentService.getExistByName(department.getName())) {
                return new PageResponse<>(HttpCode.EXIST_BY_DEPARTMENTNAME.getCode(), HttpCode.EXIST_BY_DEPARTMENTNAME.getMsg());
            }
            manageDepartmentService.update(department);
            return new PageResponse<>(HttpCode.CREATED.getCode(), HttpCode.CREATED.getMsg());
        } catch (Exception e) {
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }

}

package com.ywmobile.app.internship.DAO;

import com.ywmobile.app.internship.DTO.DepartmentDTO;
import com.ywmobile.app.internship.DTO.Paging;
import com.ywmobile.app.internship.MyBatisUtil;
import com.ywmobile.app.internship.model.Department;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDAO {

    public  int getTotalItems(String keyword){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        int totalItems =  session.selectOne("DepartmentMapper.getTotalItems",keyword);
        session.commit();
        session.close();
        return totalItems;
    }

    public  boolean getExistByName(String name){
        boolean rs = true;
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        List<Department> departments =  session.selectList("DepartmentMapper.getExistByName",name);
        if(departments.isEmpty()){
            rs = false;
        }
        session.commit();
        session.close();
        return rs;
    }

    public Department  findDepartmentById(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        Department department =  session.selectOne("DepartmentMapper.getById",id);
        session.commit();
        session.close();
        return department;
    }
    public void deleteDepartment(Integer id){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.delete("DepartmentMapper.deleteDepartment", id);
        session.commit();
        session.close();
    }

    public void save(Department department){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.insert("DepartmentMapper.insertDepartment", department);
        session.commit();
        session.close();
    }

    public void update(Department department){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.update("DepartmentMapper.updateDepartment", department);
        session.commit();
        session.close();
    }

    public List<Department> getDepartmentPaging(Paging paging) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        List<Department> departments =  session.selectList("DepartmentMapper.getDepartmentPaging",paging);
        session.commit();
        session.close();
        return departments;
    }

}


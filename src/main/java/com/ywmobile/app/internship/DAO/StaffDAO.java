package com.ywmobile.app.internship.DAO;

import com.ywmobile.app.internship.DTO.Paging;
import com.ywmobile.app.internship.DTO.StaffDTO;
import com.ywmobile.app.internship.DTO.StaffDetailDTO;
import com.ywmobile.app.internship.MyBatisUtil;
import com.ywmobile.app.internship.Request.StaffRequestPart;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffDAO {


    public List<StaffDTO> getStaffPaging(Paging paging) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        List<StaffDTO> staffDTOS = session.selectList("StaffMapper.getStaffPaging", paging);
        session.commit();
        session.close();
        return staffDTOS;
    }



    public  int getTotalStaffByKeyword (String keyword){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        int totalItems =  session.selectOne("StaffMapper.getByKeyword",keyword);
        session.commit();
        session.close();
        return totalItems;
    }

    public List<StaffDTO> getAllStaff() {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        List<StaffDTO> staffs = session.selectList("StaffMapper.getAllStaff");
        session.commit();
        session.close();
        return staffs;
    }

    public void save(StaffRequestPart staffRequest) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.insert("StaffMapper.insertStaff", staffRequest);
        session.commit();
        session.close();
    }

    public void delete(Integer id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.delete("StaffMapper.deleteStaff", id);
        session.commit();
        session.close();
    }

    public StaffDetailDTO findById(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        StaffDetailDTO staffDetailDTO = session.selectOne("StaffMapper.getById", id);
        session.commit();
        session.close();
        return staffDetailDTO;
    }

    public StaffDetailDTO getStaffByUserId(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        StaffDetailDTO staffDetailDTO = session.selectOne("StaffMapper.getStaffByUserId", id);
        session.commit();
        session.close();
        return staffDetailDTO;
    }

    public void update(StaffRequestPart staffRequestPart) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.update("StaffMapper.updateStaff", staffRequestPart);
        session.commit();
        session.close();
    }

    public void setDefaultDepartmentId(int department_id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.update("StaffMapper.setDefaultDepartmentId", department_id);
        session.commit();
        session.close();
    }


}

